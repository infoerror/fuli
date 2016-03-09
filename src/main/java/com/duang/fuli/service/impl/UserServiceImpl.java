package com.duang.fuli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UserInfoDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userInfoDao")
	private UserInfoDao userInfoDao;
	
	@Override
	public UserInfo getUserInfo(User user) {
		return userInfoDao.getUserInfo(user);
	}

}
