package com.duang.fuli.dao;

import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.domain.form.ModifyAvatarForm;
import com.duang.fuli.service.result.ModifyBasicInfoResult;


public interface UserInfoDao{

	UserInfo getUserInfo(User user);

	void modifyBasicInfo(BasicInfoForm basicInfo);

	BasicInfo getUserBasicInfo(User user);

	String getUserAvatarImageUri(User user);

	void modifyAvatar(ModifyAvatarForm modifyAvatarForm);


}
