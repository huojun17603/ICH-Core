package com.ich.module.processor;

import com.ich.module.annotation.Link;
import com.ich.module.annotation.Window;
import com.ich.module.service.ResourceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;


public class ResourcesInitProcessor implements BeanPostProcessor {
	
	@Autowired
	ResourceService resourceService;
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
		//如果注册的bean不属于Controller，直接跳出
		if (!bean.getClass().isAnnotationPresent(Controller.class)) {
		      return bean;
		}
		//得到父地址映射
		String urlMapping = null;
		if(bean.getClass().isAnnotationPresent(RequestMapping.class)){
			RequestMapping classMapping= bean.getClass().getAnnotation(RequestMapping.class);
			urlMapping = classMapping.value()[0];
		}
		//得到Controller下的所有方法，判断是否有注册信息
		Method[] methods = bean.getClass().getDeclaredMethods();
		for(Method method:methods){
			//注册菜单信息
			if(method.isAnnotationPresent(Link.class)){
				//开始注册信息
				Link link = method.getAnnotation(Link.class);
				//得到子地址映射
				if(method.isAnnotationPresent(RequestMapping.class)){
					RequestMapping methodMapping= method.getAnnotation(RequestMapping.class);
					if(urlMapping!=null){
						resourceService.addTempLinkResource(link,urlMapping+"/"+methodMapping.value()[0]);
					}else{
						resourceService.addTempLinkResource(link,methodMapping.value()[0]);
					}
				}
			}
			//注册窗口信息
			if(method.isAnnotationPresent(Window.class)){
				//开始注册信息
				Window window = method.getAnnotation(Window.class);
				//得到子地址映射
				if(method.isAnnotationPresent(RequestMapping.class)){
					RequestMapping methodMapping= method.getAnnotation(RequestMapping.class);
					if(urlMapping!=null){
						resourceService.addTempWindowResource(window,urlMapping+"/"+methodMapping.value()[0]);
					}else{
						resourceService.addTempWindowResource(window,methodMapping.value()[0]);
					}
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
		return bean;
	}

}
