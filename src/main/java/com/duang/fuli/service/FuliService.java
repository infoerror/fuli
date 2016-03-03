package com.duang.fuli.service;

import java.util.Collection;

import com.duang.fuli.domain.Fuli;

public interface FuliService {

	/**
	 * 获取最新的福利 
	 * @param top 最新条数
	 * @return
	 */
	public Collection<Fuli> getUptodateFulis(int top);

	public Collection<Fuli> getFulisOfPage(int pageIndex);
	
}
