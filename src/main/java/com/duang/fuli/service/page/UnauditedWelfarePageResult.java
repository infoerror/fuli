package com.duang.fuli.service.page;

import com.duang.fuli.dao.UnauditedWelfareDao;
import com.duang.fuli.domain.UnauditedWelfare;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.result.PageServiceResult;
import com.duang.fuli.utils.SpringContextUtils;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:54:29
 */
public class UnauditedWelfarePageResult extends PageServiceResult<UnauditedWelfare>{
	
	public static int getRecordCount(Object context) {
		UnauditedWelfarePage unauditedWelfarePage= (UnauditedWelfarePage) context;
		UnauditedWelfareDao dao=(UnauditedWelfareDao) SpringContextUtils.getBean("unauditedWelfareDao");
		return dao.getUnauditedWelfareCount(unauditedWelfarePage.getUser());
	}
	
}
