package com.duang.fuli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UserDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.mtm.Follow_User;
import com.duang.fuli.service.FansService;
import com.duang.fuli.service.result.CheckFollowResult;
import com.duang.fuli.service.result.CommonResults;
import com.duang.fuli.service.result.FollowResult;

/**
 * 
 * @author zgq
 * @date 2016年3月20日 上午11:56:42
 */
@Service("fansService")
public class FansServiceImpl implements FansService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public FollowResult follow(User user, int followId) {
		//必要验证
		if(user==null){
			return CommonResults.USER_NO_LOGIN(FollowResult.class); 
		}
		if(user.getId()==followId){
			return FollowResult.UNABLE_TO_FOLLOW_ONESELF;
		}
		
		Follow_User follow_User = new Follow_User();
		follow_User.setUserId(user.getId());
		follow_User.setFollowId(followId);
		//是否已经关注用户
     	Follow_User db_follow_user = userDao.getFollowUser(follow_User);
		if(db_follow_user!=null){
			return FollowResult.ALREADY_FOLLOWED;
		}
		User dbUser=userDao.getUserById(followId);
		if(dbUser==null){
			//会员不存在
			return FollowResult.USER_INEXISTENT;
		}
		
		userDao.follow(follow_User);
		return FollowResult.FOLLOW_SUCC;
	}

	
	
	@Override
	public CheckFollowResult checkFollow(User user, int followId) {
		//必要验证
		if(user==null){
			return CommonResults.USER_NO_LOGIN(CheckFollowResult.class); 
		}
		if(user.getId() ==followId){
			return CheckFollowResult.NOT_FOLLOW;
		}
		
		Follow_User follow_User = new Follow_User();
		follow_User.setUserId(user.getId());
		follow_User.setFollowId(followId);
		//是否已经关注用户
     	Follow_User db_follow_user = userDao.getFollowUser(follow_User);
        return db_follow_user==null?CheckFollowResult.NOT_FOLLOW:CheckFollowResult.ALREADY_FOLLOW;
	}

}
