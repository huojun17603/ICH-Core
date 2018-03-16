package com.ich.core.base;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期时间-工具类
 * @since  2015-11-30
 * @author 霍俊
 */
public class TimeUtil {
	
	/** 标准格式:yyyy-MM-dd HH:mm:ss */
	public static final String STANDARD = "yyyy-MM-dd HH:mm:ss";
	/** 一分钟毫秒值 **/
	public static final long ONE_MINUTE = 60000L;
	/** 一小时毫秒值 **/
	public static final long ONE_HOUR = 3600000L;
	/** 一天毫秒值 **/
	public static final long ONE_DAY = 86400000L;
	/** 一周毫秒值 **/
	public static final long ONE_WEEK = 604800000L;

	/**
	 * 获取参数日期的标准格式化字符串
	 * @param date 日期
	 * @return 格式化字符串（yyyy-MM-dd HH:mm:ss）
	 */
	public static String format(Date date){
		SimpleDateFormat time=new SimpleDateFormat(STANDARD);
		return time.format(date);
	}
	/**
	 * 获取格式化字符串
	 * @param date 日期
	 * @param formatStyle 格式化样式
	 * @return 格式化字符串
	 */
	public static String format(Date date,String formatStyle){
		SimpleDateFormat time=new SimpleDateFormat(formatStyle); 
		return time.format(date);
	}
	
	/**
	 * 获取当前时间点格式化字符串
	 * @param formatStyle 格式化样式
	 * @return 格式化字符串
	 */
	public static String format(String formatStyle){
		SimpleDateFormat time=new SimpleDateFormat(formatStyle); 
		return time.format(new Date());
	}

	/**
	 * 把格式化的字符串转化成日期
	 * @param timeStr 日期字符串
	 * @param formatStyle 字符串格式
	 * @return 日期
	 */
	public static Date parse(String timeStr,String formatStyle){
		if(ObjectHelper.isEmpty(timeStr))return null;
		SimpleDateFormat format = new SimpleDateFormat(formatStyle);
		Date date = null;
		try {
			date = format.parse(timeStr);
		} catch (Exception e) {
			return date;
		}
		return date;
	}

	/**
	 * 把标准格式化的字符串转化成日期
	 * @param timeStr 日期字符串（yyyy-MM-dd HH:mm:ss）
	 * @return 日期
	 */
	public static Date parse(String timeStr){
		return parse(timeStr,STANDARD);
	}

	/**
	 * 获取当前月月首日期
	 * @return yyyy-MM-01:00:00:00
	 */
	public static Date findCurrentMonth(){
		return findMonth(new Date());
	}

	/**
	 * 获取日期的月首
	 * @param date 日期
	 * @return yyyy-MM-01:00:00:00
	 */
	public static Date findMonth(Date date){
		String formatStyle = "yyyy-MM";
		String currentDate = format(date,formatStyle);
		return parse(currentDate,formatStyle);
	}
	/**
	 * 获日期的上月月首
	 * @param date 日期
	 * @return yyyy-MM-01:00:00:00
	 */
	public static Date findUpperMonth(Date date){
		return findMonth(new Date(findMonth(date).getTime()-ONE_DAY));
	}

	/**
	 * 获日期的下月月首
	 * @param date 日期
	 * @return yyyy-MM-01:00:00:00
	 */
	public static Date findLowerMonth(Date date){
		return findMonth(new Date(findMonth(date).getTime()+(ONE_DAY*32)));
	}

//	public static void main(String[] args) {
//		for(int i=0;i<100;i++){
//			Date day = new Date(new Date().getTime()+(i*ONE_DAY));
//			System.out.println(format(day));
//			System.out.println(format(findLowerMonth(day)));
//			System.out.println(format(findMonth(day)));
//			System.out.println(format(findUpperMonth(day)));
//		}
//	}
}
