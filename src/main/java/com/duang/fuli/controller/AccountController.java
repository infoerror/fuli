package com.duang.fuli.controller;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.service.UnauditedWelfareService;
import com.duang.fuli.service.WelfareService;
import com.duang.fuli.utils.SessionFlagUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/account")
public class AccountController extends BaseController{
	
	@Resource(name="unauditedWelfareService")
	private UnauditedWelfareService unauditedWelfareService;
	
	@RequestMapping(value = "/center")
	public String center(){
		return "account/index";
	}
	
	
	@RequestMapping(value = "/publishWelfare")
	public String addWelfareUI(Model model){
		Collection<WelfareTag> welfareTag=unauditedWelfareService.getAllWelfareTags();
		model.addAttribute("welfareTags",welfareTag);
		return "account/publishWelfare";
	}
	
	@RequestMapping(value = "/unauditedWelfares")
	public String unauditedWelfares(){
		return "account/unauditedWelfares";
	}
	
	
	
	
}
