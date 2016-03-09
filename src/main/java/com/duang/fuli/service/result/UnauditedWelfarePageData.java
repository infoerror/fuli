package com.duang.fuli.service.result;

import java.util.Collection;
import com.duang.fuli.domain.UnauditedWelfare;

public class UnauditedWelfarePageData extends com.duang.fuli.domain.json.Result{
	
	private int totalPages;
	private Collection<UnauditedWelfare> data;
	
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Collection<UnauditedWelfare> getData() {
		return data;
	}
	public void setData(Collection<UnauditedWelfare> data) {
		this.data = data;
	}

}
