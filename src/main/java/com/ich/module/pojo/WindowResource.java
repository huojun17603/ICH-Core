package com.ich.module.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 窗口资源持久化类
 * @author 霍俊
 */
public class WindowResource {
	
	/** 窗口资源零时注册列表 */
	public static List<WindowResource> tempList = new ArrayList<WindowResource>();
	
	/** 唯一编码  由注解<Window>提供*/
	private String code;
	/** 窗口名称 */
	private String name;
	/** 窗口引入的JSP */
	private String include;
	/** 窗口的数据信息来源URL */
	private String url;
	/**Modular*/
	private String modular;

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInclude() {
		return include;
	}
	public void setInclude(String include) {
		this.include = include;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
