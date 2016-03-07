package com.duang.fuli.dao;

import java.util.Collection;

import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;

public interface WelfareTagDao {

	public void addWelfareTag(Welfare welfare);

	public Collection<WelfareTag> getAllWelfareTags();
	
}
