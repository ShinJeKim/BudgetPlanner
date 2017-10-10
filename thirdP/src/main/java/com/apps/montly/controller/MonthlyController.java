package com.apps.montly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apps.common.StringUtil;
import com.apps.montly.domain.MonthlyVO;
import com.apps.montly.domain.WeekVO;
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
	public ModelAndView get_monthly_usage(HttpServletRequest request, HttpSession session) {
		
		//session에서 사용자 데이터 받아오기
		//UserVO inVO = (UserVO)session.getAttribute("loginUser");
		
		log.debug("================get_monthly_usage==================");
		//log.debug(inVO.toString());
		log.debug("===================================================");
		
		//id, month setting
		//String id = inVO.getId();
		//String month = "201709";
		
		//TestCase
		String id = "id2";
		String month = "201709";
		
		//월별 사용 내역 List 생성
		List<MonthlyVO> usageList = (List<MonthlyVO>) monthlySvc.get_monthly_usage(id, month);
		
		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
		int count = 0;
		
		if(usageList != null && usageList.size()>0) {
			count = usageList.size();
		}
		
		log.debug("usageList count : "+count);
		
		//수입 리스트
		List<MonthlyVO> incomeList = new ArrayList<MonthlyVO>();
		
		//지출 리스트
		List<MonthlyVO> expenseList = new ArrayList<MonthlyVO>();
				
		//합계 리스트
		List<MonthlyVO> totalList = new ArrayList<MonthlyVO>();
		
		//1. 반복문을 돌린다
		//2. 날짜가 null : 전체 총합
		//3. 카테고리로 구분한다 10, 20, null
		//4. 해당 금액을 다시 list로 만든다.
		//전체 리스트에서 세부 리스트로 분할
		for(int i=0; i<usageList.size(); i++) {
			//날짜 : null (총합)
			//category : 10 (지출)
			//category : 20 (소비)
			//category : null (합계)
			log.debug("========================usageList======================");
			log.debug("usageList("+i+")"+usageList.get(i));
			log.debug("=======================================================");
			
			if(usageList.get(i).getDay() != null) {
				
				log.debug("category : usageList("+i+")"+usageList.get(i).getCategory());
				
				if(null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("10")) {
					
					expenseList.add(usageList.get(i));
					log.debug("expenseList("+i+")"+expenseList.toString());
					
				} else if(null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("20")) {
					
					incomeList.add(usageList.get(i));
					log.debug("incomeList("+i+")"+incomeList.toString());
					
				} else if(null==usageList.get(i).getCategory() || usageList.get(i).getCategory().equals("")) {
					
					totalList.add(usageList.get(i));
					log.debug("totalList("+i+")"+totalList.toString());
					
				} //if-else
			} else {
				//날짜가 null일경우 전체 총합
			} //if-else
		} //for
		
		//지출내역 음수값 절대값 씌우기
//		for(int i=0; i<expenseList.size(); i++) {
//			if(expenseList.get(i).getUsage()<0) {
//				int absValue = Math.abs(expenseList.get(i).getUsage());
//				log.debug("absValue : "+absValue);
//				
//				//절대값을 다시 set 해주기
//				expenseList.get(i).setUsage(absValue);
//			}
//		}
		
		//날짜 List 생성
		List<WeekVO> weekList = (List<WeekVO>) monthlySvc.get_weekday(month);
		
		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
		count = 0;
		
		if(weekList != null && weekList.size()>0) {
			count = weekList.size();
		}
		
		log.debug("weekList count : "+count);
		log.debug("=======================WeekList===========================");
		log.debug(weekList.toString());
		log.debug("==========================================================");
		
		//view의 한 모양을 생성하기 : HashTable 이용
		//for문 이용
		//hashtable을 담을 list 하나 더 생성
		//1. weekList의 숫자 수정 : 1단위는 앞에 month + 0 붙여서 수정 ex ) 1 -> 20170901
		//1-2. 00단위라면 month 붙여서 수정 ex ) 10 -> 20170910
		//2. usageList와 날짜로 비교
		//3. Hashtable에 날짜, income, expense, total의 내용  put?
		//4. 다음 날짜로 넘어가면 list에 add
		
		//create ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("incomeList", incomeList);
		modelAndView.addObject("expenseList", expenseList);
		modelAndView.addObject("totalList", totalList);
		modelAndView.addObject("weekList", weekList);
		modelAndView.setViewName("monthly");
		
		return modelAndView;
	}
	
//	@RequestMapping(value="monthly/get_weekday.do")
//	public ModelAndView get_weekday(HttpServletRequest request) {
//		
//		//화면에서 날짜 받아오기
//		//String month = request.getParameter("month");
//		
//		//Test month : 추후 막아두기
//		String month = "201709";
//		
//		//List 생성
//		List<WeekVO> weekList = (List<WeekVO>) monthlySvc.get_weekday(month);
//		
//		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
//		int count = 0;
//		
//		if(weekList != null && weekList.size()>0) {
//			count = weekList.size();
//		}
//		
//		log.debug("weekList count : "+count);
//		log.debug("=======================WeekList===========================");
//		log.debug(weekList.toString());
//		log.debug("==========================================================");
//		
//		
//		//create ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//
//		modelAndView.addObject("weekList", weekList);
//		modelAndView.setViewName("monthly");
//		
//		return modelAndView;
//		
//	}
}
