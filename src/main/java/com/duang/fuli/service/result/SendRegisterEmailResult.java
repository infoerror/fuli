package com.duang.fuli.service.result;

import com.duang.fuli.service.result.Protocols.Register;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:54:18
 */
public class SendRegisterEmailResult extends ServiceResult {
	public static SendRegisterEmailResult EXPIRED_MAIL;
	public static SendRegisterEmailResult SUCC_MAIL;
	public static SendRegisterEmailResult FAIL_MAIL;
	static {
		EXPIRED_MAIL = new SendRegisterEmailResult();
		EXPIRED_MAIL.setCode(Register.EXPIRED_MAIL);
		EXPIRED_MAIL.setMsg("激活时间已经过期，请重新注册");

		SUCC_MAIL = new SendRegisterEmailResult();
		SUCC_MAIL.setCode(Register.SUCC);
		SUCC_MAIL.setMsg("成功发送激活邮件");

		FAIL_MAIL = new SendRegisterEmailResult();
		FAIL_MAIL.setCode(0);
		FAIL_MAIL.setMsg("发送激活邮件失败，请检查您的邮箱是否正确，或者联系管理员");
	}
}
