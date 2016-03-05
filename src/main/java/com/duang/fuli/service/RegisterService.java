package com.duang.fuli.service;

import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.RegisterForm;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.SendRegisterEmailResult;

public interface RegisterService {

	RegisterResult register(RegisterForm user);

	SendRegisterEmailResult sendRegisterMail(String actionUrl, InactiveAccount inactiveAccount)
			throws Exception;

	boolean authenticateEmail(String token);


}
