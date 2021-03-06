package com.duang.fuli.dao.impl;

import java.util.Collection;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.WelfareTagDao;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:23
 */
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

	@Override
	public List<WelfareTag> getHotWelfareTags(int top) {
		return this.getSqlSession().selectList("com.duang.fuli.domain.WelfareTag.selectHotWelfareTags",top);
	}

	@Override
	public WelfareTag getWelfareTagByName(String tagName) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.WelfareTag.selectWelfareTagByName",tagName);
	}

}
