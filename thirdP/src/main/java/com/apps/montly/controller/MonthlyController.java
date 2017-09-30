package com.apps.montly.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonthlyController {
private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//헤더없는 페이지
	@RequestMapping(value="org/monthly.do") 
	public String orgmonthly(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "items/budget/monthly";
	}
	
	//헤더적용 페이지
	@RequestMapping(value="budget/monthly.do") 
	public String monthly(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "monthly";
	}
}
