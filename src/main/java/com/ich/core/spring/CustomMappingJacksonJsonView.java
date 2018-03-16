package com.ich.core.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 自定义Spring视图处理器，用于实现Json格式
 * 使用方法：
 * @author 霍俊
 */
public class CustomMappingJacksonJsonView extends MappingJackson2JsonView {  
	  
    @Override  
    protected Object filterModel(Map<String, Object> model) {  
        Map<?, ?> result = (Map<?, ?>) super.filterModel(model);  
        if (result.size() == 1) {  
            return result.values().iterator().next();  
        } else {  
            return result;  
        }  
    }  
  
    protected void renderMergedOutputModel(Map<String, Object> model,  
        HttpServletRequest request, HttpServletResponse response) throws Exception {  
        Object value = filterModel(model);  
        response.setContentType("text/plain; charset=UTF-8");
        response.getOutputStream().write(value.toString().getBytes());  
        response.getOutputStream().flush();  
    }  
}  