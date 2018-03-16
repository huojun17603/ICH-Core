package com.ich.module.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 系统首页窗口信息定义注解<br/>
 * 系统启动时通过读取此注解生成窗口信息<br/>
 * @author 霍俊
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Window {
	
	/** 菜单名称 */
	String name();
	/** 菜单Code */
	String code();
	/** JSP引入地址：项目相对路径 */
	String include();
	/** modular */
	String modular();
	
}
