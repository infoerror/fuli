package com.duang.fuli.service;

import com.duang.fuli.domain.User;

import com.duang.fuli.service.result.CheckFollowResult;
import com.duang.fuli.service.result.FollowResult;

public interface FansService {

	public FollowResult follow(User user, int followId);

	public CheckFollowResult checkFollow(User user, int followId);

}
