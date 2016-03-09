package com.duang.fuli.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.UserInfoDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
@Repository("userInfoDao")
public class UserInfoImpl extends SqlSessionDaoSupport implements UserInfoDao{

	@Override
	public UserInfo getUserInfo(User user) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.UserInfo.selectUserInfoByUser",user);
	}

}
