package com.duang.fuli.dao;

import com.duang.fuli.domain.User;


public interface UserDao{

	User getUserByUsername(String username);

	void saveUser(User user);

}
