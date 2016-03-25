package com.duang.fuli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UserDao;
import com.duang.fuli.dao.UserInfoDao;
import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.domain.form.ModifyAvatarForm;
import com.duang.fuli.domain.form.ModifyPasswordForm;
import com.duang.fuli.service.UserService;
import com.duang.fuli.service.result.ModifyAvatarResult;
import com.duang.fuli.service.result.ModifyBasicInfoResult;
import com.duang.fuli.service.result.ModifyPasswordResult;
import com.duang.fuli.utils.MD5Utils;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:53:13
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userInfoDao")
	private UserInfoDao userInfoDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public UserInfo getUserInfo(User user) {
		return userInfoDao.getUserInfo(user);
	}

	@Override
	public ModifyBasicInfoResult modifyBasicInfo(BasicInfoForm basicInfo) {
		userInfoDao.modifyBasicInfo(basicInfo);
		return ModifyBasicInfoResult.MODIFY_SUCC;
	}

	@Override
	public BasicInfo getUserBasicInfo(User user) {
       return userInfoDao.getUserBasicInfo(user);
	}

	@Override
	public ModifyAvatarResult modifyAvatar(ModifyAvatarForm modifyAvatarForm) {
		userDao.modifyAvatar(modifyAvatarForm);
		return ModifyAvatarResult.MODIFY_SUCC;
	}

	@Override
	public ModifyPasswordResult modifyPassword(ModifyPasswordForm modifyPassword) {
		String oldPassword=modifyPassword.getUser().getPassword();
		if(oldPassword==null)
			oldPassword = userDao.getPasswordByUser(modifyPassword.getUser());
		String verifyPassword=MD5Utils.md5(modifyPassword.getOldPassword());
		if(!oldPassword.equals(verifyPassword)){
           			return ModifyPasswordResult.ORIGIN_PASSWORD_WRONG;
		}
		modifyPassword.setNewPassword(verifyPassword);
		
		userDao.modifyPassword(modifyPassword);
		return ModifyPasswordResult.MODIFY_SUCC;
	}


}
