package com.ich.core.http.entity;

import java.util.List;

/**
 * //分页封装函数
 * @param <T>
 */
public class PageView {
	/**
	 * 分页数据
	 */
	private List<?> records;
	
	/**
	 * 总页数
	 * 这个数是计算出来的
	 * 
	 */
	private long pageCount;


	/**
	 * 每页显示几条记录
	 */
	private int rows = 10;


	/**
	 *默认 当前页 为第一页
	 *这个数是计算出来的
	 */
	private int page = 1;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 总记录数
	 */
	private long rowCount;
	
	/**
	 * 从第几条记录开始
	 */
	private int startPage;

	/**
	 * 规定显示5个页码
	 */
	private int pagecode = 5;
	public PageView() {
	}

	/**
	 * 要获得记录的开始索引　即　开始页码
	 * @return
	 */
	public int getFirstResult(){
		return (this.page-1)* this.rows;
	}
	
	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
	
	/**
	 * 使用构造函数，，强制必需输入
	 * 每页显示数量　和　当前页
	 * @param rows　　每页显示数量
	 * @param page　当前页
	 */
	public PageView(int rows, int page){
		this.rows = rows;
		this.page = page;
	}
	/**
	 * 使用构造函数，，强制必需输入
	 * 当前页
	 * @param page　当前页
	 */
	public PageView(int page){
		this.page = page;
		startPage = (this.page - 1) * this.rows;
	}
	/**
	 * 查询结果方法
	 * 把　记录数　结果集合　放入到　PageView对象
	 * @param rowCount 总记录数
	 * @param records 结果集合
	 */

	public void setQueryResult(long rowCount, List<?> records){
		setRowCount(rowCount);
		setRecords(records);
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		setPageCount(this.rowCount % this.rows == 0?
					this.rowCount/this.rows :
					this.rowCount/this.rows+1
		);
	}
	
	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}




	/**
	 * WebTool这是一个分页工具类
	 * @author Administrator
	 *  
	 *　pagecode　要获得记录的开始索引　即　开始页码
	 *  page 　当前页
	 *　pageCount 总页数
	 *
	 *  这个工具类　返回的是页索引　PageIndex
	 *  
	 *  在这个方法中存在一个问题，每页显示数量　和　当前页、、不能为空
	 *  必需输入
	 */
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
		
	}

	public long getPageCount() {
		return pageCount;
	}
	
	public long getRowCount() {
		return rowCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


}
