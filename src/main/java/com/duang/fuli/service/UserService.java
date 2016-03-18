package com.duang.fuli.service;

import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.domain.form.ModifyAvatarForm;
import com.duang.fuli.service.result.ModifyAvatarResult;
import com.duang.fuli.service.result.ModifyBasicInfoResult;
import com.duang.fuli.service.result.ServiceResult;


public interface UserService{

	UserInfo getUserInfo(User user);

	ModifyBasicInfoResult modifyBasicInfo(BasicInfoForm basicInfo);

	BasicInfo getUserBasicInfo(User user);

	String getUserAvatarImageUri(User user);


	ModifyAvatarResult modifyAvatar(ModifyAvatarForm modifyAvatarForm);

}
