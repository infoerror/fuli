package com.duang.fuli.dao.impl;

import java.util.Collection;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.FuliDao;
import com.duang.fuli.domain.Page;
import com.duang.fuli.utils.PageUtils;

@Repository("fuliDao")
public class FuliDaoImpl extends SqlSessionDaoSupport implements FuliDao {


	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection getFulisOfPage(int pageIndex) {
		Page page = new Page();
		page.setPageCount(PageUtils.showCount);
		page.setPageIndex(pageIndex*PageUtils.showCount);
		return this.getSqlSession().selectList("com.duang.fuli.domain.Fuli.selectFulisOfPage",page);
	}


}
