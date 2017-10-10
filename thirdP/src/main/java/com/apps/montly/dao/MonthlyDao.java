package com.apps.montly.dao;

import java.util.List;

/**
 * @since 2017-10-10
 * @author 임채현
 * @version 1.0
 * Interface MonthlyDao
 *
 */
public interface MonthlyDao {
	
	public List<?> get_monthly_usage(String id, String month);
	
}
