package com.duang.fuli.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duang.fuli.controller.base.BaseController;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.service.WelfareService;
import com.duang.fuli.utils.PageUtils;


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
	
}
