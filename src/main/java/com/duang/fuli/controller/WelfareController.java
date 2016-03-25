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
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:49:32
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/welfare")
public class WelfareController extends BaseController {
	
	@Resource(name = "welfareService")
	private WelfareService welfareService;
	/*
	 * 需要改善
	 */
	@RequestMapping(value = "/uptodate/{pageindex}")
	public String uptodateList(Model model,
			@PathVariable(value = "pageindex") int pageIndex) {
		Collection<Welfare> fulis = this.welfareService
				.getFulisOfPage(pageIndex);
		model.addAttribute("results", fulis);
		model.addAttribute("showCount", PageUtils.showCount);
		return "welfare/index";
	}

	@RequestMapping(value="/{id}")
	public String detail(Model model,@PathVariable("id") int id) {
		Welfare welfare=welfareService.getWelfare(id);
		model.addAttribute("welfare", welfare);
		return "welfare/detail";
	}
	
	@RequestMapping(value="/tag/{tagName:.*}")
	public String getWelfareByTag(@PathVariable("tagName")String tagName,Model model){
		model.addAttribute("results",welfareService.getWelfaresByTagName(tagName));
		return "welfare/index";
	}
	
	@RequestMapping(value="/tags")
	public String welfareTags(Model model)
	{
		model.addAttribute("tags", welfareService.getHotWelfareTags(100));
		return "welfare/tags";
	}
	

}
