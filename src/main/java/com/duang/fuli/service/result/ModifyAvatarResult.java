package com.duang.fuli.service.result;

import com.duang.fuli.domain.form.ModifyAvatarForm;
import com.duang.fuli.service.result.Protocols.ModifyAvatar;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:53:58
 */
public class ModifyAvatarResult extends ServiceResult{

	public static final ModifyAvatarResult MODIFY_SUCC = new ModifyAvatarResult();
	
	static{
		MODIFY_SUCC.setCode(ModifyAvatar.SUCC);
		MODIFY_SUCC.setMsg("修改成功!");
	}
	
	public static ModifyAvatarResult validate(ModifyAvatarForm modifyAvatarForm) {
		if(modifyAvatarForm.getUser()==null){
			return USER_NO_LOGIN(ModifyAvatarResult.class);
		}
		return null;
		
	}

	public boolean modifySucc() {
		return MODIFY_SUCC==this;
	}

}
