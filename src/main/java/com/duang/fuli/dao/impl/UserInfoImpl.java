package com.duang.fuli.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.UserInfoDao;
import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.domain.form.ModifyAvatarForm;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:11
 */
@Repository("userInfoDao")
public class UserInfoImpl extends SqlSessionDaoSupport implements UserInfoDao{

	@Override
	public UserInfo getUserInfo(User user) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.UserInfo.selectUserInfoByUser",user);
	}

	@Override
	public void modifyBasicInfo(BasicInfoForm basicInfo) {
		 this.getSqlSession().update("com.duang.fuli.domain.UserInfo.updateBasicInfo",basicInfo);
	}

	@Override
	public BasicInfo getUserBasicInfo(User user) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.UserInfo.selectUserBasicInfoByUser",user);
	}

	@Override
	public String getUserAvatarImageUri(User user) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.UserInfo.selectUserAvatarByUser",user);
	}

	@Override
	public void modifyAvatar(ModifyAvatarForm modifyAvatarForm) {
		this.getSqlSession().update("com.duang.fuli.domain.UserInfo.updateAvatar", modifyAvatarForm);	
	}

	
}
