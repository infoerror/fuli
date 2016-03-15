package com.duang.fuli.dao;

import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;


public interface UserDao{

	User getUserByUsername(String username);

	void saveUser(User user);

	User login(User user);

	void saveUserInfo(UserInfo userInfo);

}
