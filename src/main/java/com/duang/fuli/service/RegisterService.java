package com.duang.fuli.service;

import com.duang.fuli.domain.InactiveAccount;
import com.duang.fuli.domain.form.RegisterForm;
import com.duang.fuli.service.result.RegisterResult;
import com.duang.fuli.service.result.SendRegisterEmailResult;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:43
 */
public interface RegisterService {

	RegisterResult register(RegisterForm user);

	SendRegisterEmailResult sendRegisterMail(String actionUrl, InactiveAccount inactiveAccount)
			throws Exception;

	boolean authenticateEmail(String token);


}
