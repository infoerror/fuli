package com.duang.fuli.domain.page;

/**
 * 
 * @author zgq
 * @date 2016年3月22日 下午11:44:06
 */
public class PageBase {
	private int startIndex;
	private int currentPage;
	private int pageCount;
	private int pageSize=10;
	/**
	 * 是否要强制 重新计算pageCount 
	 * 如果传入的pageCount 小于或等于0，也会重新计算pageCount
	 */
	private boolean forciblyFetchCount;
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isForciblyFetchCount() {
		return forciblyFetchCount;
	}
	public void setForciblyFetchCount(boolean forciblyFetchCount) {
		this.forciblyFetchCount = forciblyFetchCount;
	}
	
}
