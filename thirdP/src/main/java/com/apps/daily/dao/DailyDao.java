package com.apps.daily.dao;

import com.apps.common.DTO;
import com.apps.common.WorkDiv;

public interface DailyDao extends WorkDiv{
	
	public abstract DTO do_searchOne(DTO dto);
}
