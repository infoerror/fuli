package com.duang.fuli.dao;

import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.LoginForm;

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

}
