package com.duang.fuli.domain.page;

import java.io.Serializable;
import com.duang.fuli.domain.User;

public class UnauditedWelfarePage implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean needLoadCount;
	private int currentPage;
	private int totalPages;
	private User user;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isNeedLoadCount() {
		return needLoadCount;
	}

	public void setNeedLoadCount(boolean needLoadCount) {
		this.needLoadCount = needLoadCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
