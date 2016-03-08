package com.duang.fuli.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UnauditedWelfareDao;
import com.duang.fuli.dao.WelfareDao;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.service.WelfareService;


@Service("welfareService")
public class WelfareServiceImpl implements WelfareService {

	@Resource(name = "welfareDao")
	private WelfareDao welfareDao;
	
	
	@Resource(name = "unauditedWelfareDao")
	private UnauditedWelfareDao unauditedWelfareDao;

	@Override
	public Collection<Welfare> getUptodateFulis(int top) {
		return null;
	}

	@Override
	public Collection<Welfare> getFulisOfPage(int pageIndex) {
		return welfareDao.getFulisOfPage(pageIndex);
	}

	






}
