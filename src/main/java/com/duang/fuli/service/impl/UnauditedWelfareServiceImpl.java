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
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.domain.mtm.Welfare_Tag;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.UnauditedWelfareService;
import com.duang.fuli.service.result.AddWelfareResult;
import com.duang.fuli.service.result.UnauditedWelfarePageData;
import com.duang.fuli.utils.PageUtils;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:53:08
 */
@Service("unauditedWelfareService")
public class UnauditedWelfareServiceImpl implements UnauditedWelfareService {

	@Resource(name = "unauditedWelfareDao")
	private UnauditedWelfareDao unauditedWelfareDao;
	@Resource(name = "welfareTagDao")
	private WelfareTagDao welfareTagDao;

	@Override
	public AddWelfareResult addUnauditedWelfare(WelfareForm welfareForm) {
		
		User author = welfareForm.getAuthor();
		int[] welfareTagIds = welfareForm.getWelfareTagIds();
		
		UnauditedWelfare welfare=new UnauditedWelfare();
		welfare.setTitle(welfare.getTitle());
		welfare.setContent(welfare.getContent());
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
		
		return AddWelfareResult.ADD_WELFARE_SUCC;
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
		unauditedWelfarePageData.setCode(0);
		unauditedWelfarePageData.setData(unauditedWelfares);
		unauditedWelfarePageData.setTotalPages(totalPages);
		return unauditedWelfarePageData;
	}

}
