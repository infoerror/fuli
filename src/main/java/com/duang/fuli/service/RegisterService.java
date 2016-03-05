package com.duang.fuli.service;

import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.RegisterForm;
import com.duang.fuli.service.result.RegisterResult;

public interface RegisterService {

	RegisterResult register(RegisterForm user);

	boolean sendRegisterMail(String actionUrl, InactiveAccount inactiveAccount)
			throws Exception;

	boolean authenticateEmail(String token);


}
