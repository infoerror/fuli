package com.duang.fuli.dao;

import java.util.Collection;

import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.Welfare_Tag;

public interface WelfareDao {

	public Collection<Welfare> getFulisOfPage(int pageIndex);

	public void addWelfare(Welfare welfare);

	public void addTagsToWelfare(Welfare_Tag welfare_Tag);
	
}
