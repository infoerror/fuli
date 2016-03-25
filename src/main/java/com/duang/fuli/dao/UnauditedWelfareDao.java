package com.duang.fuli.dao;

import java.util.List;

import com.duang.fuli.domain.UnauditedWelfare;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.mtm.Welfare_Tag;
import com.duang.fuli.domain.page.UnauditedWelfarePage;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:50:14
 */
public interface UnauditedWelfareDao {

	public void addUnauditedWelfare(UnauditedWelfare welfare);
	
	public void addTagsToUnauditedWelfare(Welfare_Tag welfare_Tag);

	public int getUnauditedWelfareCount(User user);

	public List<UnauditedWelfare> getUnauditedWelfaresForPage(
			UnauditedWelfarePage unauditedPage);
	
}
