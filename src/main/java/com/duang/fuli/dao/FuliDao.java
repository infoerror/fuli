package com.duang.fuli.dao;

import java.util.Collection;
import com.duang.fuli.domain.Fuli;

public interface FuliDao {

	public Collection<Fuli> getFulisOfPage(int pageIndex);
	
}
