package com.ich.core.listener;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数信息-容器
 * @since  2015-12-9
 * @author 霍俊
 */
public class SystemConfig {

	private static Map<String,String> params = new HashMap<String, String>();

	
	public static String getParams(String key) {
		return params.get(key);
	}

	public static void setParams(String key,String value) {
		params.put(key, value);
	}
	
	public static boolean containsKey(String key){
		return params.containsKey(key);
	}
}
