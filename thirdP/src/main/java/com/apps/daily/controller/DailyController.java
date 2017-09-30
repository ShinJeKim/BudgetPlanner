package com.apps.daily.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DailyController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//헤더없는 페이지
	@RequestMapping(value="org/daily.do") 
	public String orgdaily(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "items/budget/daily";
	}
	
	//헤더적용 페이지
	@RequestMapping(value="budget/daily.do") 
	public String daily(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "daily";
	}
}
