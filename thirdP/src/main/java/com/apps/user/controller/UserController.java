package com.apps.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.apps.user.service.UserSvc;

@Controller
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserSvc userSvc;
	
	
}
