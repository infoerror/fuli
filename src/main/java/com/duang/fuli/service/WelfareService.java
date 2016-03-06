package com.duang.fuli.service;

import java.util.Collection;

import com.duang.fuli.domain.Welfare;

public interface WelfareService {

	/**
	 * 获取最新的福利 
	 * @param top 最新条数
	 * @return
	 */
	public Collection<Welfare> getUptodateFulis(int top);

	public Collection<Welfare> getFulisOfPage(int pageIndex);
	
}
