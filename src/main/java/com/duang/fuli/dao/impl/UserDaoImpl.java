package com.duang.fuli.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.LoginForm;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:01
 */
@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

	@Override
	public User getUserByUsername(String username) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.User.selectByUsername",username);
	}

	@Override
	public void saveUser(User user) {
		this.getSqlSession().insert("com.duang.fuli.domain.User.insertUser",user);
	}

	@Override
	public User login(LoginForm loginForm) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.User.selectByUsernameAndPassword",loginForm);
	}

	@Override
	public void saveUserInfo(UserInfo userInfo) {
		this.getSqlSession().insert("com.duang.fuli.domain.UserInfo.insertUserInfo",userInfo);		
	}

}
