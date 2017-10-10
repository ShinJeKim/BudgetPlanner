package com.apps.montly.service;

import java.util.List;

/**
 * @since 2017-10-10
 * @author 임채현
 * @version 1.0
 * interface MonthlySvc
 *
 */
public interface MonthlySvc {
	
	/**
	 * 월별 사용 내역 조회
	 * @param id
	 * @param month
	 * @return list
	 */
	public List<?> get_monthly_usage(String id, String month);
}
