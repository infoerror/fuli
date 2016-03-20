package com.duang.fuli.service.result;

import com.duang.fuli.domain.form.ModifyPasswordForm;
import com.duang.fuli.service.result.Protocols.ModifyPassword;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午8:52:46
 */
public class ModifyPasswordResult extends ServiceResult {


	private static final ModifyPasswordResult PASSWORD_FORMAT_ERROR;
	
	private static final ModifyPasswordResult TWO_PASSWORD_INEQUAL;

	public static final ModifyPasswordResult ORIGIN_PASSWORD_WRONG;

	public static final ModifyPasswordResult MODIFY_SUCC;
	
	static{
		MODIFY_SUCC=new ModifyPasswordResult();
		MODIFY_SUCC.setCode(ModifyPassword.SUCC);
		MODIFY_SUCC.setMsg("密码修改成功");
		
		PASSWORD_FORMAT_ERROR=new ModifyPasswordResult();
		PASSWORD_FORMAT_ERROR.setCode(ModifyPassword.PASSWORD_FORMAT_ERROR);
		PASSWORD_FORMAT_ERROR.setMsg("密码只能在6位到16位之间哦");
		
		TWO_PASSWORD_INEQUAL=new ModifyPasswordResult();
		TWO_PASSWORD_INEQUAL.setCode(ModifyPassword.TWO_PASSWORD_INEQUAL);
		TWO_PASSWORD_INEQUAL.setMsg("两次密码不一致哦");
		
		ORIGIN_PASSWORD_WRONG=new ModifyPasswordResult();
		ORIGIN_PASSWORD_WRONG.setCode(ModifyPassword.ORIGIN_PASSWORD_WRONG);
		ORIGIN_PASSWORD_WRONG.setMsg("原来密码错误哦");
	}
	
	public static ModifyPasswordResult validate(
			ModifyPasswordForm modifyPassword) {
		
		if(modifyPassword.getUser()==null){
			return USER_NO_LOGIN(ModifyPasswordResult.class);
		}
		
		String oldPassword = modifyPassword.getOldPassword();
		String newPassword = modifyPassword.getNewPassword();
		String confirmNewPassword=modifyPassword.getConfirmNewPassword();
		
		if (oldPassword == null || oldPassword.length() < 6
				|| oldPassword.length() > 16) {
			return PASSWORD_FORMAT_ERROR;
		}
		
		if(newPassword==null || newPassword.length()<6 ||  newPassword.length()>16){
			return PASSWORD_FORMAT_ERROR;
		}

		if(!newPassword.equals(confirmNewPassword))
			return TWO_PASSWORD_INEQUAL;
		
		return null;

	}

	public boolean modifySuccessfully(){
		 return getCode()==ModifyPassword.SUCC;
	}
	
}
