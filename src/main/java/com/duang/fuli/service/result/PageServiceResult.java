package com.duang.fuli.service.result;

import java.util.List;

/**
 * 
 * @author zgq
 * @date 2016年3月25日 下午4:48:56
 */
abstract public class PageServiceResult<T> extends ServiceResult{
	
	private int pageCount;
	private int currentPage;
	private List<T> results;

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
