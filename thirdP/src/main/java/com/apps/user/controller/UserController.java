package com.apps.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.user.service.UserSvc;

@Controller
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserSvc userSvc;
	
	@RequestMapping(value="user/login.do") 
	public String main(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		
		return "user/login";
	}
}
