package com.duang.fuli.service.result;

import java.util.Collection;
import com.duang.fuli.domain.UnauditedWelfare;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:54:29
 */
public class UnauditedWelfarePageData extends ServiceResult{
	
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
