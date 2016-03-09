package com.duang.fuli.service.impl;

import java.sql.Timestamp;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UnauditedWelfareDao;
import com.duang.fuli.dao.WelfareTagDao;
import com.duang.fuli.domain.UnauditedWelfare;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.Welfare_Tag;
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.domain.json.Result;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.UnauditedWelfareService;
import com.duang.fuli.service.result.AddWelfareResult;
import com.duang.fuli.service.result.UnauditedWelfarePageData;
import com.duang.fuli.utils.JsonUtils;
import com.duang.fuli.utils.PageUtils;


@Service("unauditedWelfareService")
public class UnauditedWelfareServiceImpl implements UnauditedWelfareService {

	@Resource(name = "unauditedWelfareDao")
	private UnauditedWelfareDao unauditedWelfareDao;
	@Resource(name = "welfareTagDao")
	private WelfareTagDao welfareTagDao;
	
	private static final String TITLE_FORMAT_ERROR_JSON;
	private static final String CONTENT_FORMAT_ERROR_JSON;
	private static final String ADD_WELFARE_SUCC_JSON;
	static{
		Result result = new Result();
		result.setError_no(0);
		result.setMsg("添加成功");
		ADD_WELFARE_SUCC_JSON =JsonUtils.toString(result);
		
		result.setError_no(10001);
		result.setError("标题长度不能少于10字");
		TITLE_FORMAT_ERROR_JSON =JsonUtils.toString(result);
		
		result.setError_no(10001);
		result.setError("内容长度不能少于100字");
		CONTENT_FORMAT_ERROR_JSON =JsonUtils.toString(result);
		
	}
	

	@Override
	public AddWelfareResult addUnauditedWelfare(WelfareForm welfareForm) {
		AddWelfareResult result  = new AddWelfareResult();
		String title= welfareForm.getTitle();
		User author  = welfareForm.getAuthor();
		String content= welfareForm.getContent();
		int[] welfareTagIds=welfareForm.getWelfareTagIds();
		
		if(title==null || title.length()<10){
			result.setJson(TITLE_FORMAT_ERROR_JSON);
			return result;
		}
		
		if(content==null || content.length()<50){
			result.setJson(CONTENT_FORMAT_ERROR_JSON);
			return result;
		}
		
		UnauditedWelfare welfare=new UnauditedWelfare();
		welfare.setTitle(title);
		welfare.setContent(content);
		Timestamp publishTime = new Timestamp(System.currentTimeMillis());
		welfare.setPublishTime(publishTime);
		welfare.setAuthor(author);
		unauditedWelfareDao.addUnauditedWelfare(welfare);
		
		for(int welfareTagId:welfareTagIds){
			Welfare_Tag welfare_Tag = new Welfare_Tag();
			welfare_Tag.setWelfareId(welfare.getId());
			welfare_Tag.setWelfareTagId(welfareTagId);
			unauditedWelfareDao.addTagsToUnauditedWelfare(welfare_Tag);
		}
		result.setJson(ADD_WELFARE_SUCC_JSON);
		
		return result;
	}


	@Override
	public Collection<WelfareTag> getAllWelfareTags() {
		return welfareTagDao.getAllWelfareTags();
	}


	@Override
	public UnauditedWelfarePageData getUnauditedWelfare(
			UnauditedWelfarePage unauditedPage) {
		
		UnauditedWelfarePageData unauditedWelfarePageData = new UnauditedWelfarePageData();
		int totalPages=unauditedPage.getTotalPages();
		User currentUser = unauditedPage.getUser();
		int currentPage=unauditedPage.getCurrentPage();
	    currentPage =  currentPage==0?1:currentPage;
	    
		if(unauditedPage.isNeedLoadCount()){
			//第一次加载
			int count=unauditedWelfareDao.getUnauditedWelfareCount(currentUser);
			totalPages = count/PageUtils.showCount+1;
		}
		currentUser.setPageIndex((currentPage-1)*PageUtils.showCount);
		currentUser.setPageCount(PageUtils.showCount);
		
		Collection<UnauditedWelfare> unauditedWelfares=unauditedWelfareDao.getUnauditedWelfares(currentUser);
		unauditedWelfarePageData.setError_no(0);
		unauditedWelfarePageData.setData(unauditedWelfares);
		unauditedWelfarePageData.setTotalPages(totalPages);
		return unauditedWelfarePageData;
	}

}
