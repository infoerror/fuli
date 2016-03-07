package com.duang.fuli.dao.impl;

import java.util.Collection;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.WelfareTagDao;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;

@Repository("welfareTagDao")
public class WelfareTagDaoImpl extends SqlSessionDaoSupport implements WelfareTagDao {

	@Override
	public void addWelfareTag(Welfare welfare) {
		 this.getSqlSession().insert("com.duang.fuli.domain.WelfareTag.insertWelfareTag",welfare);
	}

	@Override
	public Collection<WelfareTag> getAllWelfareTags() {
		return this.getSqlSession().selectList("com.duang.fuli.domain.WelfareTag.selectAllWelfareTags");
	}


}
