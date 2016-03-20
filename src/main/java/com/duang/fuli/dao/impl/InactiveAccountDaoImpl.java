package com.duang.fuli.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.InactiveAccountDao;
import com.duang.fuli.domain.InactiveAccount;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:50:40
 */
@Repository("inactiveAccountDao")
public class InactiveAccountDaoImpl extends SqlSessionDaoSupport implements InactiveAccountDao{


	@Override
	public void saveInactiveAccount(InactiveAccount inactiveAccount) {
		  this.getSqlSession().insert("com.duang.fuli.domain.InactiveAccount.insertInactiveAccount", inactiveAccount);
	}

	@Override
	public InactiveAccount getByToken(String token) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.InactiveAccount.selectByToken",token);
	}

	@Override
	public void deleteByUsername(String username) {
		  this.getSqlSession().delete("com.duang.fuli.domain.InactiveAccount.deleteByUsername", username);
	}

}
