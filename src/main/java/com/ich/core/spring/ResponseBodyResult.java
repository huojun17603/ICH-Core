package com.ich.core.spring;

import com.ich.core.base.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ResponseBodyResult {
	
	public static Map<Integer,Object> resultMap = new HashMap<Integer,Object>();

	public static void setResult(Object o){
		resultMap.put(ThreadLocalUtil.getID(),o);
	}
	
	public static Object getResult(){
		Object o = resultMap.get(ThreadLocalUtil.getID());
		resultMap.remove(ThreadLocalUtil.getID());
		return o;
	}
	
	public static void clear(){
		resultMap.clear();
	}
	
}
