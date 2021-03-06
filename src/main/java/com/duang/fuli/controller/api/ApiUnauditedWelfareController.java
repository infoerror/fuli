package com.duang.fuli.controller.api;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.UnauditedWelfareService;
import com.duang.fuli.service.page.UnauditedWelfarePageResult;
import com.duang.fuli.service.result.AddWelfareResult;
import com.duang.fuli.utils.JsonUtils;
import com.duang.fuli.web.utils.SessionFlags;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:49:51
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/api/unauditedWelfare")
public class ApiUnauditedWelfareController extends JSONController {
	@Resource(name = "unauditedWelfareService")
	private UnauditedWelfareService unauditedWelfareService;
	
	@RequestMapping(value = "/addWelfare",method=RequestMethod.POST)
	public void add(HttpSession session,@RequestBody WelfareForm welfareForm) throws IOException{
	    User user = (User) session.getAttribute(SessionFlags.LOGINED_USER_FLAG);
		welfareForm.setAuthor(user);
		AddWelfareResult result=unauditedWelfareService.addUnauditedWelfare(welfareForm);
		writeJson(result);
	}
	
	/**
	 * @deprecated
	 * @param response
	 * @param session
	 * @param unauditedPage
	 * @throws IOException
	 */
	/*
	 @RequestMapping(value = "/myList",method=RequestMethod.POST)
	public void myList(HttpServletResponse response,HttpSession session,@RequestBody UnauditedWelfarePage unauditedPage) throws IOException{
	    User user = (User) session.getAttribute(SessionFlags.LOGINED_USER_FLAG);
	    unauditedPage.setUser(user);
	    UnauditedWelfarePageResult unauditedWelfareData=unauditedWelfareService.getUnauditedWelfaresForPage(unauditedPage);
        writeJson(unauditedWelfareData);		
	}
	*/
}
