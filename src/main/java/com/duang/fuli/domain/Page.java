package com.duang.fuli.domain;

public class Page {
	private Long tempID;
	private int pageCount;
	private int pageIndex;
	private int printCount;
	private int start;
	public Long getTempID() {
		return tempID;
	}
	public void setTempID(Long tempID) {
		this.tempID = tempID;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPrintCount() {
		return printCount;
	}
	public void setPrintCount(int printCount) {
		this.printCount = printCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
}
