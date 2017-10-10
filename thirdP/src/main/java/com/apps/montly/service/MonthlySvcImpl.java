package com.apps.montly.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.montly.dao.MonthlyDao;

/**
 * @since 2017-10-10
 * @author 임채현
 * @version 1.0
 * MonthlySvcImpl implements MonthlySvc
 *
 */
@Service
public class MonthlySvcImpl implements MonthlySvc {
	
	private Logger log = LoggerFactory.getLogger(MonthlySvcImpl.class);
	
	@Autowired
	MonthlyDao monthlyDao;
	
	@Override
	public List<?> get_monthly_usage(String id, String month) {
		log.debug("2============MonthlySvcImpl===============");
		log.debug("2============get_monthly_usage===============");
		log.debug("id : "+ id + " month : "+ month);
		log.debug("2==================================");
		
		return monthlyDao.get_monthly_usage(id, month);
	}

}
