package com.duang.fuli.service;

import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.domain.form.ModifyAvatarForm;
import com.duang.fuli.domain.form.ModifyPasswordForm;
import com.duang.fuli.service.result.ModifyAvatarResult;
import com.duang.fuli.service.result.ModifyBasicInfoResult;
import com.duang.fuli.service.result.ModifyPasswordResult;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:56
 */
public interface UserService{

	UserInfo getUserInfo(User user);

	ModifyBasicInfoResult modifyBasicInfo(BasicInfoForm basicInfo);

	BasicInfo getUserBasicInfo(User user);

	ModifyAvatarResult modifyAvatar(ModifyAvatarForm modifyAvatarForm);

	ModifyPasswordResult modifyPassword(ModifyPasswordForm modifyPassword);

}
