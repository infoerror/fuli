package com.duang.fuli.service.impl;

import java.sql.Timestamp;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.WelfareDao;
import com.duang.fuli.dao.WelfareTagDao;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.Welfare_Tag;
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.domain.json.Result;
import com.duang.fuli.service.WelfareService;
import com.duang.fuli.service.result.AddWelfareResult;
import com.duang.fuli.utils.JsonUtils;


@Service("welfareService")
public class WelfareServiceImpl implements WelfareService {

	@Resource(name = "welfareDao")
	private WelfareDao welfareDao;
	@Resource(name = "welfareTagDao")
	private WelfareTagDao welfareTagDao;

	@Override
	public Collection<Welfare> getUptodateFulis(int top) {
		return null;
	}

	@Override
	public Collection<Welfare> getFulisOfPage(int pageIndex) {
		return welfareDao.getFulisOfPage(pageIndex);
	}

	
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
	public AddWelfareResult addWelfare(WelfareForm welfareForm) {
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
		
		Welfare welfare=new Welfare();
		welfare.setTitle(title);
		welfare.setContent(content);
		Timestamp publishTime = new Timestamp(System.currentTimeMillis());
		welfare.setPublishTime(publishTime);
		welfare.setViewCount(0);
		welfare.setAuthor(author);
		welfareDao.addWelfare(welfare);
		
		for(int welfareTagId:welfareTagIds){
			Welfare_Tag welfare_Tag = new Welfare_Tag();
			welfare_Tag.setWelfareId(welfare.getId());
			welfare_Tag.setWelfareTagId(welfareTagId);
			welfareDao.addTagsToWelfare(welfare_Tag);
		}
		result.setJson(ADD_WELFARE_SUCC_JSON);
		
		return result;
	}

	@Override
	public Collection<WelfareTag> getAllWelfareTags() {
		return welfareTagDao.getAllWelfareTags();
	}

}
