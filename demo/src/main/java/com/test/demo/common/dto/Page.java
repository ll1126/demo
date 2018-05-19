package com.test.demo.common.dto;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PAGE_NUMBER = "pageNum"; //当前页
	public static final String PAGE_SIZE = "pageSize"; //每页大小
	public static final String TOTAL_SIZE = "totalPageNum"; //总页数
	public static final String CURRENT_PAGE = "pageNextNum";
	public static final int DEFAULT_SIZE = 10;
	public static final String PAGE_LIST = "page_list";
	
	
	private Integer pageNum=1; //当前页
	private Integer pageSize=10;//每页大小
	private Integer totalPageNum;//总页数
	private Integer totalRows;//总条数
	private Integer pageCurrentNum=1;
	private List<?> pageList;//
	private Object queryParameter;//查询参数

	private Integer offset=1; //当前页
	private Integer limit=10;//每页大小
	
	
	
	
	public Page() {
		super();
	}


	public Page(Integer pageNum, Integer pageSize, Object queryParameter) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.queryParameter = queryParameter;
	}
	

	public Integer getPageCurrentNum() {
		return pageCurrentNum;
	}


	public void setPageCurrentNum(Integer pageCurrentNum) {
		this.pageCurrentNum = pageCurrentNum;
	}


	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}


	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(Integer totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public Integer getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	public List<?> getPageList() {
		return pageList;
	}
	public void setPageList(List<?> pageList) {
		this.pageList = pageList;
	}

	public Object getQueryParameter() {
		return queryParameter;
	}
	public void setQueryParameter(Object queryParameter) {
		this.queryParameter = queryParameter;
	}


	public Integer getOffset() {
		return offset;
	}


	public void setOffset(Integer offset) {
		this.offset = offset;
		this.pageNum = offset;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
		this.limit = limit;
		this.pageSize = limit;
	}


	
	
}
