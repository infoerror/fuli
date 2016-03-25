package com.duang.fuli.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.UnauditedWelfareService;
import com.duang.fuli.service.UserService;
import com.duang.fuli.service.page.UnauditedWelfarePageResult;
import com.duang.fuli.service.result.PageServiceResult;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:49:24
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	
	@Resource(name="unauditedWelfareService")
	private UnauditedWelfareService unauditedWelfareService;
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value = "/center")
	public String center(Model model){
		UserInfo userInfo=userService.getUserInfo(getCurrentUser());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("user", getCurrentUser());
		return "user/index";
	}
	
	@RequestMapping(value = "/modifyBasicInfo")
	public String modifyBasicInfoUI(Model model){
		BasicInfo basicInfo=userService.getUserBasicInfo(getCurrentUser());
		basicInfo.setNickname(getCurrentUser().getNickname());
		model.addAttribute("basicInfo", basicInfo);
		return "user/modifyBasicInfo";
	}
	
	@RequestMapping(value = "/modifyAvatar")
	public String modifyAvatarUI(Model model){
		model.addAttribute("avatarUrl", getCurrentUser().getAvatarUrl());
		return "user/modifyAvatar";
	}	
	
	@RequestMapping(value = "/modifyPassword")
	public String modifyPasswordUI(Model model){
		return "user/modifyPassword";
	}	
	
	@RequestMapping(value = "/publishWelfare")
	public String addWelfareUI(Model model){
		Collection<WelfareTag> welfareTag=unauditedWelfareService.getAllWelfareTags();
		model.addAttribute("welfareTags",welfareTag);
		return "user/publishWelfare";
	}
	
	
	@RequestMapping(value = "/unauditedWelfares/{page}")
	public String unauditedWelfares(@PathVariable("page")int page,Model model){
		UnauditedWelfarePage unauditedWelfarePage  =new UnauditedWelfarePage();
		unauditedWelfarePage.setCurrentPage(page);
		unauditedWelfarePage.setUser(getCurrentUser());
		PageServiceResult<Welfare> pageResult= (PageServiceResult<Welfare>) unauditedWelfareService.getUnauditedWelfaresForPage(unauditedWelfarePage);
		model.addAttribute("welfares",pageResult.getResults());
		sequencePage(pageResult,model);
		return "user/unauditedWelfares";
	}
	
	
}
