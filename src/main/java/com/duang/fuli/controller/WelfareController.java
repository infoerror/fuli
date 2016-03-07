package com.duang.fuli.controller;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.service.WelfareService;
import com.duang.fuli.service.result.AddWelfareResult;
import com.duang.fuli.utils.PageUtils;
import com.duang.fuli.utils.SessionFlagUtils;


@Controller
@Scope("prototype")
@RequestMapping(value = "/welfare")
public class WelfareController extends BaseController {
	@Resource(name = "welfareService")
	private WelfareService welfareService;

	@RequestMapping(value = "/uptodate/{pageindex}")
	public String uptodateList(Model model,
			@PathVariable(value = "pageindex") int pageIndex) {
		Collection<Welfare> fulis= this.welfareService.getFulisOfPage(pageIndex);
		model.addAttribute("fulis",fulis);
		model.addAttribute("showCount", PageUtils.showCount);
		return "welfare/index";
	}
	
	@RequestMapping(value = "/addWelfare",method=RequestMethod.POST)
	public void addWelfare(HttpServletResponse response,HttpSession session,@RequestBody WelfareForm welfareForm) throws IOException{
	    User user = (User) session.getAttribute(SessionFlagUtils.LOGINED_USER_FLAG);
		welfareForm.setAuthor(user);
		AddWelfareResult result=welfareService.addWelfare(welfareForm);
		writeJson(result.getJson(), response);
	}
}
