package com.duang.fuli.dao;

import com.duang.fuli.domain.InactiveAccount;


public interface InactiveAccountDao {
	
	InactiveAccount getByToken(String token);

	void saveInactiveAccount(InactiveAccount inactiveAccount);

	void deleteByUsername(String username);

}
