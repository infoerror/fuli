package com.duang.fuli.service.result;

import com.duang.fuli.service.result.Protocols.CheckFollow;

/**
 * 
 * @author zgq
 * @date 2016年3月20日 下午7:48:22
 */
public class CheckFollowResult extends ServiceResult{
	
	public static final CheckFollowResult ALREADY_FOLLOW;
	
	public static final CheckFollowResult NOT_FOLLOW;
	
	static{
		ALREADY_FOLLOW=new CheckFollowResult();
		ALREADY_FOLLOW.setCode(CheckFollow.ALREADY_FOLLOW);
		ALREADY_FOLLOW.setMsg("已经关注了！");
		
		NOT_FOLLOW=new CheckFollowResult();
		NOT_FOLLOW.setCode(CheckFollow.NOT_FOLLOW);
		NOT_FOLLOW.setMsg("还没有关注！");
	}
	

}
