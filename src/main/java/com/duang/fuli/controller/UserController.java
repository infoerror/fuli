package com.duang.fuli.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.BasicInfo;
import com.duang.fuli.domain.UserInfo;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.UnauditedWelfareService;
import com.duang.fuli.service.UserService;
import com.duang.fuli.service.result.UnauditedWelfarePageData;
import com.duang.fuli.utils.PageUtils;

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
		model.addAttribute("basicInfo", basicInfo);
		return "user/modifyBasicInfo";
	}
	
	@RequestMapping(value = "/modifyAvatar")
	public String modifyAvatarUI(Model model){
		String imageUri=userService.getUserAvatarImageUri(getCurrentUser());
		model.addAttribute("imageUri", imageUri);
		return "user/modifyAvatar";
	}	
	
	
	@RequestMapping(value = "/publishWelfare")
	public String addWelfareUI(Model model){
		Collection<WelfareTag> welfareTag=unauditedWelfareService.getAllWelfareTags();
		model.addAttribute("welfareTags",welfareTag);
		return "user/publishWelfare";
	}
	
	
	@RequestMapping(value = "/unauditedWelfares")
	public String unauditedWelfares(Model model){
		UnauditedWelfarePage unauditedWelfarePage  =new UnauditedWelfarePage();
		unauditedWelfarePage.setCurrentPage(1);
		unauditedWelfarePage.setUser(getCurrentUser());
		unauditedWelfarePage.setNeedLoadCount(true);
		UnauditedWelfarePageData pageData=unauditedWelfareService.getUnauditedWelfare(unauditedWelfarePage);
		
		model.addAttribute("welfares",pageData.getData());
		int startPage=PageUtils.computerStartPage(1,pageData.getTotalPages());
		model.addAttribute("startPage", startPage);
		int endPage =PageUtils.computerEndPage(1,pageData.getTotalPages());
		model.addAttribute("endPage", endPage);
	
		model.addAttribute("totalPages",pageData.getTotalPages());
		
		return "user/unauditedWelfares";
	}
	
	
}
