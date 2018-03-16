package com.ich.core.base;

import java.util.Random;
import java.util.UUID;

/**
 * 各种id生成策略
 * <p>Title: IDUtils</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年7月22日下午2:32:10
 * @version 1.0
 */
public class IDUtils {
	
	/**
	 * 生成uuid
	 * @author jiangL
	 * 2015-6-10-上午10:42:38
	 * @return
	 */
	public static String  createUUId(){
		 UUID uuid = UUID.randomUUID(); 
		 String id = uuid.toString().replaceAll("-", "");
		 return id;
	}

}
