package com.apps.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apps.user.domain.UserVO;
import com.apps.user.service.UserSvc;

@Controller
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserSvc userSvc;
	
	//메인페이지 호출
	@RequestMapping(value="main.do") 
	public String login(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "login";
	}
	//로그인시 유효성
	@RequestMapping(value="do_login.do", method=RequestMethod.POST)
	public ModelAndView do_login(HttpServletRequest request,HttpServletResponse res) throws IOException {
		
		log.debug("1======================================");
		log.debug("do_login.do");
		log.debug("1======================================");
		
		ModelAndView modelAndView = new ModelAndView();
		UserVO inVO = new UserVO();
		UserVO outVO = new UserVO();
		
		inVO.setId(request.getParameter("id"));
		inVO.setPassword(request.getParameter("password"));

		outVO = (UserVO)userSvc.do_login(inVO);
		
		if(outVO !=null) {
			
			log.debug(outVO.toString());
			
			HttpSession session = request.getSession(true);
			session.setAttribute("loginUser", outVO);
			session.setAttribute("ID", outVO.getId());
			res.sendRedirect("budget/daily.do");
			modelAndView.setViewName("daily");
			
		} else {
			
			int passwordFlag = userSvc.do_check_passwd(inVO);
			
			if(passwordFlag == 0) {
				modelAndView.addObject("message", "passwordFailure");
			}
			
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="createUser.do")
	public String goCreateUser(HttpServletRequest request) {
		log.debug("0=====================================");
		log.debug("goCreateUser()");
		log.debug("0=====================================");
		
		return "createUser";
	}

	@RequestMapping(value="do_check_id.do")
	@ResponseBody
	public String do_check_id(HttpServletRequest request) {
		
		log.debug("1======================================");
		log.debug("do_check_id");
		log.debug("1======================================");
		
		int flag = 0;
		
		String id = request.getParameter("id");
		
		log.debug("id : "+id);
		
		flag = userSvc.do_check_id(id);
		
		log.debug("flag(control) : "+flag);
		
		return flag+"";
	}
	
	@RequestMapping(value="do_save.do")
	public void do_save(HttpServletRequest request,HttpServletResponse res) throws IOException{
		
		UserVO inVO = new UserVO();
		
		inVO.setId(request.getParameter("id"));
		inVO.setPassword(request.getParameter("password"));
		inVO.setName(request.getParameter("name"));
		inVO.setEmail(request.getParameter("email"));
		
		int fixed_income = Integer.parseInt(request.getParameter("fixed_income"));
		inVO.setFixed_income(fixed_income);
		
		int balance = Integer.parseInt(request.getParameter("balance"));
		inVO.setBalance(balance);
		
		log.debug("inVO : "+inVO.toString());
		
		int flag = 0;
		flag = userSvc.do_save(inVO);
		
		log.debug("flag : "+flag);
		
		res.sendRedirect("main.do");
	}
	
	@RequestMapping(value="logout.do")
	public void logout(HttpServletRequest req,HttpServletResponse res, HttpSession session) throws IOException {
		log.debug("0=====================================");
		log.debug("logout()");
		log.debug("0=====================================");
		session.invalidate();
		res.sendRedirect("main.do");
		
	}
	
	/*원본
	@RequestMapping(value="user/login.do") 
	public String main(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "items/user/login";
	}
	
	@RequestMapping(value="user/createUser.do")
	public String goCreateUser(HttpServletRequest request) {
		log.debug("0=====================================");
		log.debug("goCreateUser()");
		log.debug("0=====================================");
		
		return "itmes/user/createUser";
	}

	@RequestMapping(value="user/do_check_id.do")
	@ResponseBody
	public String do_check_id(HttpServletRequest request) {
		
		log.debug("1======================================");
		log.debug("do_check_id");
		log.debug("1======================================");
		
		int flag = 0;
		
		String id = request.getParameter("id");
		
		log.debug("id : "+id);
		
		flag = userSvc.do_check_id(id);
		
		log.debug("flag(control) : "+flag);
		
		return flag+"";
	}
	
	@RequestMapping(value="user/do_login.do", method=RequestMethod.POST)
	public ModelAndView do_login(HttpServletRequest request) {
		
		log.debug("1======================================");
		log.debug("do_login.do");
		log.debug("1======================================");
		
		ModelAndView modelAndView = new ModelAndView();
		
		UserVO inVO = new UserVO();
		UserVO outVO = new UserVO();
		
		inVO.setId(request.getParameter("id"));
		inVO.setPassword(request.getParameter("password"));

		outVO = (UserVO)userSvc.do_login(inVO);
		
		if(outVO !=null) {
			
			log.debug(outVO.toString());
			modelAndView.addObject("message", "success");
			modelAndView.setViewName("items/user/test");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("loginUser", outVO);
			
		} else {
			
			int passwordFlag = userSvc.do_check_passwd(inVO);
			
			if(passwordFlag == 0) {
				modelAndView.addObject("message", "passwordFailure");
			}
			
			modelAndView.setViewName("itmes/user/login");
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="user/do_save.do")
	public ModelAndView do_save(HttpServletRequest request) throws IOException{
		
		ModelAndView modelAndView = new ModelAndView();
		
		UserVO inVO = new UserVO();
		
		inVO.setId(request.getParameter("id"));
		inVO.setPassword(request.getParameter("password"));
		inVO.setName(request.getParameter("name"));
		inVO.setEmail(request.getParameter("email"));
		
		int fixed_income = Integer.parseInt(request.getParameter("fixed_income"));
		inVO.setFixed_income(fixed_income);
		
		int balance = Integer.parseInt(request.getParameter("balance"));
		inVO.setBalance(balance);
		
		log.debug("inVO : "+inVO.toString());
		
		int flag = 0;
		flag = userSvc.do_save(inVO);
		
		log.debug("flag : "+flag);
		
		modelAndView.setViewName("itmes/user/login");
		
		return modelAndView;
	}
	*/
}
