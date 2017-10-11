package com.apps.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value="admin/category.do")
	public String categoryUpdate(HttpServletRequest request) {
		log.debug("0=====================================");
		log.debug("admin()");
		log.debug("0=====================================");
		
		return "categoryUpdate";
	}
	
	@RequestMapping(value="admin/userList.do")
	public String userlist(HttpServletRequest request) {
		log.debug("0=====================================");
		log.debug("admin()");
		log.debug("0=====================================");
		
		return "userList";
	}
}
