package com.duang.fuli.service.result;

import java.io.Serializable;



import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.service.result.Protocols.ModifyBasicInfo;
import com.duang.fuli.utils.StringUtils;
import com.duang.fuli.utils.XSSUtils;

public class ModifyBasicInfoResult extends ServiceResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static ModifyBasicInfoResult NICKNAME_FORMAT_WRONG;

	public final static ModifyBasicInfoResult MODIFY_SUCC;
	
	public final static ModifyBasicInfoResult NICKNAME_CONTAIN_DANGEROUS_CHAR;

	static {
		MODIFY_SUCC = new ModifyBasicInfoResult();
		MODIFY_SUCC.setCode(ModifyBasicInfo.SUCC);
		MODIFY_SUCC.setMsg("修改成功!");
		
		NICKNAME_FORMAT_WRONG = new ModifyBasicInfoResult();
		NICKNAME_FORMAT_WRONG.setCode(ModifyBasicInfo.NICKNAME_FORMAT_WRONG);
		NICKNAME_FORMAT_WRONG.setMsg("昵称只能1-10字之內哦!");

		NICKNAME_CONTAIN_DANGEROUS_CHAR = new ModifyBasicInfoResult();
		NICKNAME_CONTAIN_DANGEROUS_CHAR.setCode(ModifyBasicInfo.NICKNAME_FORMAT_WRONG);
		NICKNAME_CONTAIN_DANGEROUS_CHAR.setMsg("昵称含有危险字符!");
	}

	/**
	 * would return null if the data has passed validation successfully.
	 * 
	 * @param basicInfo
	 * @return
	 */
	public static ModifyBasicInfoResult validate(BasicInfoForm basicInfo) {
		if(basicInfo.getUser()==null){
			return USER_NO_LOGIN(ModifyBasicInfoResult.class);
		}
		
		String nickname = basicInfo.getNickname();
		if (StringUtils.isBlank(nickname) || nickname.length() > 10) {
                return NICKNAME_FORMAT_WRONG;
		}
		if(XSSUtils.simplyValidate(nickname)){
			return NICKNAME_CONTAIN_DANGEROUS_CHAR;
		}
		
		return null;
	}

}
