package com.duang.fuli.controller.api;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.User;
import com.duang.fuli.service.FansService;
/**
 * 
 * @author zgq
 * @date 2016年3月20日 上午11:46:30
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/api/fans")
public class ApiFansController extends JSONController{
	
	@Resource(name="fansService")
	private FansService fansService;

	@RequestMapping(value = "/follow")
	public void follow(@RequestParam("id")int userId) throws IOException{
	    User user=getCurrentUser();
        writeJson(fansService.follow(user,userId));		
	}
	@RequestMapping(value = "/checkFollow")
	public void checkFollow(@RequestParam("id")int userId) throws IOException{
	    User user=getCurrentUser();
        writeJson(fansService.checkFollow(user,userId));		
	}

}
