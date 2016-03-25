package com.duang.fuli.dao;

import java.util.Collection;
import java.util.List;

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

	public List<WelfareTag> getHotWelfareTags(int top);

	public WelfareTag getWelfareTagByName(String tagName);
	
}
