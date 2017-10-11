package com.apps.chart.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="chart.do")
	public String categoryUpdate(HttpServletRequest request) {
		log.debug("0=====================================");
		log.debug("chart()");
		log.debug("0=====================================");
		
		return "chart";
	}
}
