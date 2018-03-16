package com.ich.core.http.controller;

import com.ich.core.base.JsonUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.other.CustomException;
import com.ich.core.http.service.CoreService;
import com.ich.core.listener.SystemConfig;
import com.ich.international.pojo.ILocaleMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 核心控制层，结合HttpResponse返回值
 * 推荐用法：建议通过callback进行返回
 * 如果要重定义错误处理，请使用新的类
 * 如果想使用HttpResponse形式的返回值，可在WEB项目中定义一个自己的通用控制层，并继承本控制层
 * Created by 霍俊 on 2017/7/2 0002.
 */
public class CoreController {

    protected final Logger logger = Logger.getLogger(CoreController.class);

    public static final String SESSION_USERID = "";

    @Autowired
    private CoreService coreService;

    /**

     * JSON，JSONP 通用返回数据处理
     * @param callback 返回的方法
     * @param json 返回的字符
     * @return
     */
    public String callback(String callback,String json){
        if(ObjectHelper.isEmpty(callback)){
            if(ObjectHelper.isEmpty(json)) return "";
            return json;
        }else{
            return callback+"("+json+");";
        }
    }

    public String callback(String callback,HttpResponse response){
        if(ObjectHelper.isNotEmpty(coreService)&&ObjectHelper.isNotEmpty(response))coreService.executeCallback(response);
        if(ObjectHelper.isEmpty(callback)){
            if(ObjectHelper.isEmpty(response)) return "";
            return JsonUtils.objectToJson(response);
        }else{
            return callback+"("+JsonUtils.objectToJson(response)+");";
        }
    }

    public String callback(String callback,Map<String,Object> response){
        if(response.containsKey(HttpResponse.RETURN_STATUS)&&response.containsKey(HttpResponse.RETURN_MSG)){
            HttpResponse responsex = new HttpResponse((Integer)response.get(HttpResponse.RETURN_STATUS),(String)response.get(HttpResponse.RETURN_MSG));
            if(ObjectHelper.isNotEmpty(coreService)&&ObjectHelper.isNotEmpty(response))coreService.executeCallback(responsex);
        }
        if(ObjectHelper.isEmpty(callback)){
            if(ObjectHelper.isEmpty(response)) return "";
            return JsonUtils.objectToJson(response);
        }else{
            return callback+"("+JsonUtils.objectToJson(response)+");";
        }
    }
    /**
     * 验证请求返回状态，是否是成功状态
     */
    protected Boolean validStatus(Map<String,Object> model){
        if(model.containsKey(HttpResponse.RETURN_STATUS)){
            if(model.get(HttpResponse.RETURN_STATUS).equals(HttpResponse.HTTP_OK)){
                return true;
            }
        }
        return false;
    }

    /**
     * 创建并返回一个标准成功返回Model
     */
    protected Map<String,Object> getSuccessMap() {
        String RETURN_MSG_OK = "OK";
        if(SystemConfig.containsKey("RETURN_MSG_OK")){
            RETURN_MSG_OK = SystemConfig.getParams("RETURN_MSG_OK");//系统参数配置：RETURN_MSG_OK
        }
        Map<String,Object> model = new HashMap<String, Object>();
        model.put(HttpResponse.RETURN_STATUS, HttpResponse.HTTP_OK);
        model.put(HttpResponse.RETURN_MSG, RETURN_MSG_OK);
        return model;
    }
    /**
     * 创建并返回一个标准成功返回Model，并向其写入data
     */
    protected Map<String,Object> getSuccessMap(Object data) {
        Map<String,Object> model = getSuccessMap();
        model.put(HttpResponse.RETURN_DATA,data);
        return model;
    }

    /**
     * 返回一个自定义错误类型的Model
     * @param data 错误的返回数据
     * @param msg 错误提示
     * @param status 自定义返回状态
     * @return Model
     */
    protected Map<String,Object> getFailMap(Integer status,String msg,Object data) {
        Map<String,Object> model = new HashMap<String, Object>();
        model.put(HttpResponse.RETURN_STATUS, status);
        model.put(HttpResponse.RETURN_MSG, msg);
        model.put(HttpResponse.RETURN_DATA,data);
        return model;
    }

    /**
     * 返回一个标准错误类型的Model
     * @param data 错误的返回数据
     * @param msg 错误提示
     * @return Model
     */
    protected Map<String,Object> getFailMap(String msg,Object data) {
        return getFailMap(HttpResponse.HTTP_ERROR, msg, data);
    }


    /**
     * 返回一个标准错误类型的Model,没有数据的
     * @param msg 错误提示
     * @return Model
     */
    protected Map<String,Object> getFailMap(String msg) {
        Map<String,Object> model = new HashMap<String, Object>();
        model.put(HttpResponse.RETURN_STATUS, HttpResponse.HTTP_ERROR);
        model.put(HttpResponse.RETURN_MSG, msg);
        return model;
    }

    /**
     * 错误统一处理方案
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler
    public @ResponseBody Object exceptionHandler(Exception exception, HttpServletRequest request, HttpServletResponse response){
        /*判断请求类型是不是ajax的*/
        exception.printStackTrace();
        String error_msg = "系统未知错误！请刷新后重试！";
        if(SystemConfig.containsKey("error_msg")){
            error_msg = SystemConfig.getParams("error_msg");
        }else{
            logger.warn("No configuration parameters : error_msg");
        }
        if(isAjaxRequest(request)) {
          /*如果是Ajax请求将错误信息返回到ajax date*/
            HttpResponse res = null;
            if(exception instanceof CustomException) {
                /*如果自定义的错误显示自定义的错误类型*/
                error_msg = exception.getMessage();
                String value = ILocaleMessage.DATAMAP.get(error_msg);
                if(ObjectHelper.isNotEmpty(value)) error_msg = value;
                res = new HttpResponse(((CustomException) exception).getCode(),error_msg);
            }else{
                /*如果不是自定义的错误显示系统性错误或同一错误*/
                String value = ILocaleMessage.DATAMAP.get(error_msg);
                if(ObjectHelper.isNotEmpty(value)) error_msg = value;
                res = new HttpResponse(HttpResponse.HTTP_ERROR,error_msg);
            }
            Object callback = request.getParameter("callback");
            if(ObjectHelper.isNotEmpty(callback))
                return callback((String)callback,JsonUtils.objectToJson(res));
            return callback("",JsonUtils.objectToJson(res));
        }else{
            /*如果不是Ajax的重从定向到错误界面*/
            Map<String,Object> model = new HashMap<>();
            if(exception instanceof CustomException) {
                model.put(HttpResponse.RETURN_STATUS,((CustomException) exception).getCode());
                model.put(HttpResponse.RETURN_MSG,exception.getMessage());
            }else{
                model.put(HttpResponse.RETURN_STATUS,HttpResponse.HTTP_ERROR);
            }
            String error_redirect_index = "/";
            if(SystemConfig.containsKey("error_redirect_index")){
                error_redirect_index = SystemConfig.getParams("error_redirect_index");
            }else{
                logger.warn("No configuration parameters : error_redirect_index");
            }
            return new ModelAndView("redirect:" + error_redirect_index);
        }
    }
    /**
     * 判断是否ajax请求.
     * 可以看到Ajax 请求多了个 x-requested-with ，可以利用它，
     * request.getHeader("x-requested-with"); 为 null，则为传统同步请求，为 XMLHttpRequest，则为Ajax 异步请求。
     * 注意：JSONP不会被判断为AJAX
     * @param  request
     * @return 是否ajax请求.
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xr = request.getHeader("X-Requested-With");
        return(xr!=null&&"XMLHttpRequest".equalsIgnoreCase(xr));
    }


}
