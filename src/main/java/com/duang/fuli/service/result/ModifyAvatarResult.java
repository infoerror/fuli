package com.duang.fuli.service.result;

public class ModifyAvatarResult extends ServiceResult{

	public static final ModifyAvatarResult MODIFY_SUCC = new ModifyAvatarResult();
	
	static{
		MODIFY_SUCC.setCode(Protocols.OPERATE_SUCC);
		MODIFY_SUCC.setMsg("修改成功!");
		
	}

}
