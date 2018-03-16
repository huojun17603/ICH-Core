package com.ich.core.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义Spring视图处理器，用于实现Jsonp的返还格式
 * @author 霍俊
 */
public class CustomMappingJacksonJsonpView extends CustomMappingJacksonJsonView {  
    /** 
     * Default content type. Overridable as bean property. 
     */  
    public static final String DEFAULT_CONTENT_TYPE = "application/javascript";  
  
    @Override  
    public String getContentType() {  
        return DEFAULT_CONTENT_TYPE;  
    }  
  
    @Override  
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {  
        if ("GET".equals(request.getMethod().toUpperCase())) {  
            @SuppressWarnings("unchecked")  
            Map<String, String[]> params = request.getParameterMap();  
  
            if (params.containsKey("callback")) {  
                response.getOutputStream().write(new String(params.get("callback")[0] + "(").getBytes());  
                super.render(model, request, response);  
                response.getOutputStream().write(new String(");").getBytes());  
                response.setContentType(DEFAULT_CONTENT_TYPE);  
            } else {  
                super.render(model, request, response);  
            }  
        } else {  
            super.render(model, request, response);  
        }  
    }  
  
  
}  