package com.ich.core.http.entity;


/**
 * http响应返回值
 * 如有需要可以对状态值进行扩展
 * @author 霍俊
 * 2015-6-10-下午3:20:01
 */
public class HttpResponse {

	/** 请求成功 */
	public final static int HTTP_OK = 0;
	/** 请求失败 */
	public final static int HTTP_ERROR = 1;
	/** 登录失效 */
	public final static int HTTP_INVALID = 2;
	/** 没有权限 */
	public final static int HTTP_NO_POWER = 3;
	/** 没有额度 */
	public final static int HTTP_NO_MONEY = 4;
	/** 请求超时 */
	public final static int HTTP_TIMEOUT = 10;

	/** 返回状态 */
	public final static String RETURN_STATUS = "status";
	/** 返回说明 */
	public final static String RETURN_MSG = "msg";
	/** 返回数据 */
	public final static String RETURN_DATA = "data";

	/** 通用(成功信息)返回内容*/
	public final static String HTTP_MSG_OK = "OK";
	/** 通用(失败信息)返回内容*/
	public final static String HTTP_MSG_ERROR = "ERROR";

	private int status;
	
	private String msg;
	
	private Object data;
	
	public HttpResponse( int status, String msg ) {
		this( status, msg, null );
	}
	
	public HttpResponse( int status, String msg, Object data ) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
