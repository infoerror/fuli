package com.duang.fuli.service;

import java.util.Collection;

import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.service.result.AddWelfareResult;

public interface WelfareService {

	/**
	 * 获取最新的福利 
	 * @param top 最新条数
	 * @return
	 */
	public Collection<Welfare> getUptodateFulis(int top);

	public Collection<Welfare> getFulisOfPage(int pageIndex);

	public Welfare getWelfare(int id);


	
}
