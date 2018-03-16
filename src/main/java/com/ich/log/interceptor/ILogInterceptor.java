package com.ich.log.interceptor;

import com.ich.config.service.IConfigService;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.controller.CoreController;
import com.ich.core.http.other.IPUtils;
import com.ich.log.dto.ILogLocalData;
import com.ich.log.pojo.ILog;
import com.ich.log.service.ILogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class ILogInterceptor extends HandlerInterceptorAdapter {

    protected final Logger logger = Logger.getLogger(ILogInterceptor.class);

    @Autowired
    private ILogService iLogService;
    @Autowired
    private IConfigService iConfigService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) return true;
        ILog iLog = new ILog();
        String uri = request.getRequestURI();
        String userid = (String) request.getSession().getAttribute(CoreController.SESSION_USERID);
        String ssessionid = request.getRequestedSessionId();
        String ip = IPUtils.findRequestIP(request);
        String accept = request.getHeader("accept");
        String useragent = request.getHeader("user-agent");
        String referer = request.getHeader("referer");
        Date creattime = new Date();
        Integer state = 1;
        iLog.setUri(uri);
        iLog.setUserid(userid);
        iLog.setSessionid(ssessionid);
        iLog.setIp(ip);
        iLog.setAccept(accept);
        iLog.setUseragent(useragent);
        iLog.setReferer(referer);
        iLog.setCreattime(creattime);
        iLog.setState(state);
        String OUT = iConfigService.getParams("OUT-REQUEST");
        if(ObjectHelper.isNotEmpty(OUT)&&OUT.equals("1")){
            logger.info("[ilog]uri:"+uri);
            logger.info("[ilog]userid:"+userid);
            logger.info("[ilog]ssessionid:"+ssessionid);
            logger.info("[ilog]ip:"+ip);
            logger.info("[ilog]accept:"+accept);
            logger.info("[ilog]useragent:"+useragent);
            logger.info("[ilog]referer:"+referer);
            logger.info("[ilog]creattime:"+creattime);
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                logger.info("[ilog][request-param]"+ name +":"+ valueStr);
            }
        }
        ILogLocalData.setData("ilog",iLog);
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(!(handler instanceof HandlerMethod)) return;
        ILog iLog = (ILog)ILogLocalData.getData("ilog");
        if(ObjectHelper.isEmpty(iLog)){
            logger.error("[ilog]callback:post error!");
            logger.error("[ilog]callback:URL:" + request.getRequestURL());
            return;//TODO 后续处理错误
        }
        String callback = (String)ILogLocalData.getData("ilog_callbak");
        iLog.setCallback(callback);
        logger.debug("[ilog]callback:"+callback);
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(!(handler instanceof HandlerMethod)) return;
        Date day = new Date();
        ILog iLog = (ILog)ILogLocalData.getData("ilog");
        if(ObjectHelper.isEmpty(iLog)){
            logger.error("[ilog]callback:after error!");
            logger.error("[ilog]callback:URL:" + request.getRequestURL());
            return;//TODO 后续处理错误
        }
        Integer timeout = (int)(day.getTime()-iLog.getCreattime().getTime());
        iLog.setTimeout(timeout);
        iLog.setState(2);
        iLogService.addInterceptorILog(iLog);
        ILogLocalData.clear();
        logger.debug("[ilog]size:"+ILogLocalData.getDataSize());
        logger.debug("[ilog]timeout:"+timeout);
    }

}
