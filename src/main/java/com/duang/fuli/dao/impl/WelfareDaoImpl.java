package com.duang.fuli.dao.impl;

import java.util.Collection;




import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.WelfareDao;
import com.duang.fuli.domain.Page;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.Welfare_Tag;
import com.duang.fuli.utils.PageUtils;

@Repository("welfareDao")
public class WelfareDaoImpl extends SqlSessionDaoSupport implements WelfareDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection getFulisOfPage(int pageIndex) {
		Page page = new Page();
		page.setPageCount(PageUtils.showCount);
		page.setPageIndex(pageIndex*PageUtils.showCount);
		return this.getSqlSession().selectList("com.duang.fuli.domain.Welfare.selectFulisOfPage",page);
	}

	@Override
	public void addWelfare(Welfare welfare) {
		 this.getSqlSession().insert("com.duang.fuli.domain.Welfare.insertWelfare",welfare);
	}

	@Override
	public void addTagsToWelfare(Welfare_Tag welfare_Tag) {
		 this.getSqlSession().insert("com.duang.fuli.domain.Welfare.buildWelfareAndTagRelation",welfare_Tag);
	}


}