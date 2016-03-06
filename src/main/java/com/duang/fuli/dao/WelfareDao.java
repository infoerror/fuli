package com.duang.fuli.dao;

import java.util.Collection;
import com.duang.fuli.domain.Welfare;

public interface WelfareDao {

	public Collection<Welfare> getFulisOfPage(int pageIndex);
	
}
