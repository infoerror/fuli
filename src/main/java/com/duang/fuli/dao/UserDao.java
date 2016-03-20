package com.duang.fuli.dao;

import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.LoginForm;
import com.duang.fuli.domain.form.ModifyPasswordForm;
import com.duang.fuli.domain.mtm.Follow_User;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:50:18
 */
public interface UserDao{

	User getUserByUsername(String username);

	void saveUser(User user);

	User login(LoginForm loginForm);

	void saveUserInfo(UserInfo userInfo);

	void modifyPassword(ModifyPasswordForm modifyPassword);

	String getPasswordByUser(User user);

	User getUserById(int userId);

	
	/*
	 * follow start
	 */
	void follow(Follow_User follow_User);

	Follow_User getFollowUser(Follow_User follow_User);

	/*
	 * follow end
	 */
}
