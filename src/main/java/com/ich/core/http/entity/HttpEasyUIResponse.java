package com.ich.core.http.entity;

import java.util.List;

public class HttpEasyUIResponse extends HttpResponse{

	/** DataGrid 分页搜索-记录总和 */
	public final static String HTTP_DATA_PAGE_TOTAL = "total";
	/** DataGrid 分页搜索-页面数据 */
	public final static String HTTP_DATA_PAGE_ROWS = "rows";

	public HttpEasyUIResponse(int status, String msg, Object data) {
		super(status, msg, data);
	}

	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
