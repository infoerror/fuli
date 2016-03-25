package com.duang.fuli.domain.page;

import com.duang.fuli.domain.User;
import com.duang.fuli.service.annotation.PageResult;
import com.duang.fuli.service.page.UnauditedWelfarePageResult;

/**
 * 
 * @author zgq
 * @date 2016年3月25日 下午4:28:39
 */
@PageResult(UnauditedWelfarePageResult.class)
public class UnauditedWelfarePage extends PageBase{
	
	/**
	 * 
	 */
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
