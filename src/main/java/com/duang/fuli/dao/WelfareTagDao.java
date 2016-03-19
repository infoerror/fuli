package com.duang.fuli.dao;

import java.util.Collection;

import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:50:31
 */
public interface WelfareTagDao {

	public void addWelfareTag(Welfare welfare);

	public Collection<WelfareTag> getAllWelfareTags();
	
}
