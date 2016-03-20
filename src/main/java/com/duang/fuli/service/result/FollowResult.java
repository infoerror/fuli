package com.duang.fuli.service.result;

import com.duang.fuli.service.result.Protocols.Follow;

public class FollowResult extends  ServiceResult{
	
	public static final FollowResult USER_INEXISTENT;

	public static final FollowResult FOLLOW_SUCC;

	public static final FollowResult ALREADY_FOLLOWED;
	
	static{
		USER_INEXISTENT = new FollowResult();
		USER_INEXISTENT.setCode(Follow.USER_INEXISTENT);
		USER_INEXISTENT.setMsg("会员不存在哦");
		
		ALREADY_FOLLOWED = new FollowResult();
		ALREADY_FOLLOWED.setCode(Follow.ALREADY_FOLLOWED);
		ALREADY_FOLLOWED.setMsg("已经关注过此用户了哦");
		
		FOLLOW_SUCC = new FollowResult();
		FOLLOW_SUCC.setCode(Follow.SUCC);
		FOLLOW_SUCC.setMsg("关注成功");
	}
	
	
}
