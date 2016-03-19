package com.duang.fuli.service;

import java.util.Collection;

import com.duang.fuli.domain.Welfare;

/**
 * 这个接口的几个方法需要重名名和完善
 * @author zgq
 * @date 2016年3月19日 下午1:52:07
 */
public interface WelfareService {

	/**
	 * 
	 * 获取最新的福利 
	 * @param top 最新条数
	 * @return
	 */
	public Collection<Welfare> getUptodateFulis(int top);

	public Collection<Welfare> getFulisOfPage(int pageIndex);

	public Welfare getWelfare(int id);


	
}
