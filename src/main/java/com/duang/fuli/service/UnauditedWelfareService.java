package com.duang.fuli.service;

import java.util.Collection;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.form.WelfareForm;
import com.duang.fuli.domain.page.UnauditedWelfarePage;
import com.duang.fuli.service.result.AddWelfareResult;
import com.duang.fuli.service.result.UnauditedWelfarePageData;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:47
 */
public interface UnauditedWelfareService {
	
	Collection<WelfareTag> getAllWelfareTags();
	AddWelfareResult addUnauditedWelfare(WelfareForm welfareForm);

	UnauditedWelfarePageData getUnauditedWelfare(UnauditedWelfarePage unauditedPage);
}
