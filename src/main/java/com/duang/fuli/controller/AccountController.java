package com.duang.fuli.controller;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.service.WelfareService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/account")
public class AccountController extends BaseController{
	
	@Resource
	private WelfareService welfareService;
	
	@RequestMapping(value = "/center")
	public String center(){
		return "account/index";
	}
	
	@RequestMapping(value = "/myWelfares")
	public String myWelfares(){
		return "account/index";
	}
	
}
