package com.duang.fuli.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.FuliDao;
import com.duang.fuli.domain.Fuli;
import com.duang.fuli.service.FuliService;

@Service("fuliService")
public class FuliServiceImpl implements FuliService {

	@Resource(name = "fuliDao")
	private FuliDao fuliDao;

	@Override
	public Collection<Fuli> getUptodateFulis(int top) {
		return null;
	}

	@Override
	public Collection<Fuli> getFulisOfPage(int pageIndex) {
		return fuliDao.getFulisOfPage(pageIndex);
	}

}
