package com.apps.user.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.user.domain.UserVO;
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
	
	@RequestMapping(value="user/do_login.do")
	public String do_login(HttpServletRequest request) {
		UserVO inVO = new UserVO();
		UserVO outVO = new UserVO();
		
		inVO.setId(request.getParameter("id"));
		inVO.setPassword(request.getParameter("password"));
		
		Hashtable<String, String> message = new Hashtable<String, String>();
		
		String work_div = request.getParameter("work_div");
		
		if(work_div.equals("do_login")) {
			//login
			outVO = (UserVO)userSvc.do_login(inVO);
			message = outVO.getParam();
			
			String message_div = message.get("message_div").toString();
			String message_cont = message.get("message").toString();
			
			if(null != message_div && message_div.equals("LOGIN.SUCCESS")) {
				//TODO : session에  outVO 추가
				//TODO : 일일 List 화면으로 이동
				
				return "redirect:temp.do";
			}
		}
		
		return "";
	}
	
	
//	@RequestMapping(value="user/do_save.do")
//	public String do_save(HttpServletRequest request) throws IOException{
//		UserVO inVO = new UserVO();
//		
//		inVO.setId(request.getParameter("id"));
//		inVO.setPassword(request.getParameter("password"));
//		inVO.setName(request.getParameter("name"));
//		inVO.setEmail(request.getParameter("email"));
//		
//		int fixed_income = Integer.parseInt(request.getParameter("fixed_income"));
//		inVO.setFixed_income(fixed_income);
//		
//		int balance = Integer.parseInt(request.getParameter("balance"));
//		inVO.setBalance(balance);
//		
//		inVO.setReg_dt(request.getParameter("reg_dt"));
//		inVO.setMod_dt(request.getParameter("mod_dt"));
//		
//		int delete_flag = Integer.parseInt(request.getParameter("delete_flag"));
//		inVO.setDelete_flag(delete_flag);
//		
//		int admin_flag = Integer.parseInt(request.getParameter("admin_flag"));
//		inVO.setAdmin_flag(admin_flag);
//		
//		log.debug("inVO : "+inVO.toString());
//		
//		int flag = 0;
//		flag = userSvc.do_save(inVO);
//		
//		log.debug("flag : "+flag);
//		
//		return "redirect:createUser.do";
//	}
}
