package com.apps.montly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apps.montly.domain.MonthlyVO;
import com.apps.montly.service.MonthlySvc;
import com.apps.user.domain.UserVO;

/**
 * @since 2017-10-10
 * @author 임채현
 * @version 1.0
 * MonthlyController
 */
@Controller
public class MonthlyController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MonthlySvc monthlySvc;
	
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
	
	//get_monthly_usage
	@RequestMapping(value="monthly/get_monthly_usage.do")
	public ModelAndView get_monthly_usage(HttpSession session) {
		
		//session에서 사용자 데이터 받아오기
		//UserVO inVO = (UserVO)session.getAttribute("loginUser");
		
		log.debug("================loginUser==================");
		//log.debug(inVO.toString());
		log.debug("===========================================");
		
		//id, month setting
		//String id = inVO.getId();
		//String month = "201709";
		
		//TestCase
		String id = "id2";
		String month = "201709";
		
		//List 생성
		List<MonthlyVO> usageList = (List<MonthlyVO>) monthlySvc.get_monthly_usage(id, month);
		
		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
		int count = 0;
		
		if(usageList != null && usageList.size()>0) {
			count = usageList.size();
		}
		
		log.debug("count : "+count);
		
		//음수값 절대값 씌우기
		for(int i=0; i<usageList.size(); i++) {
			if(usageList.get(i).getUsage()<0) {
				int absValue = Math.abs(usageList.get(i).getUsage());
				log.debug("absValue : "+absValue);
				
				//절대값을 다시 set 해주기
				usageList.get(i).setUsage(absValue);
			}
		}
		
		//create ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("usageList", usageList);
		modelAndView.setViewName("items/budget/monthly");
		
		return modelAndView;
	}
}
