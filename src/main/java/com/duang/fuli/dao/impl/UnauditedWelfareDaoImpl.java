package com.duang.fuli.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.UnauditedWelfareDao;
import com.duang.fuli.domain.UnauditedWelfare;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.mtm.Welfare_Tag;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:50:45
 */
@Repository("unauditedWelfareDao")
public class UnauditedWelfareDaoImpl extends SqlSessionDaoSupport implements UnauditedWelfareDao {

	@Override
	public void addUnauditedWelfare(UnauditedWelfare welfare) {
		 this.getSqlSession().insert("com.duang.fuli.domain.UnauditedWelfare.insertUnauditedWelfare",welfare);
	}

	@Override
	public void addTagsToUnauditedWelfare(Welfare_Tag welfare_Tag) {
		this.getSqlSession().insert("com.duang.fuli.domain.UnauditedWelfare.buildUnauditedWelfareAndTagRelation",welfare_Tag);		
	}

	@Override
	public int getUnauditedWelfareCount(User user) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.UnauditedWelfare.selectUserUnauditedWelfareCount",user);
	}

	@Override
	public List<UnauditedWelfare> getUnauditedWelfaresForPage(UnauditedWelfarePage unauditedWelfarePage) {
		return this.getSqlSession().selectList("com.duang.fuli.domain.UnauditedWelfare.selectUserUnauditedWelfaresForPage",unauditedWelfarePage);
	}


}
