package com.duang.fuli.dao;

import com.duang.fuli.domain.InactiveAccount;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:50:09
 */
public interface InactiveAccountDao {
	
	InactiveAccount getByToken(String token);

	void saveInactiveAccount(InactiveAccount inactiveAccount);

	void deleteByUsername(String username);

}
