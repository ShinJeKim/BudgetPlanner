package com.apps.montly.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
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
import com.apps.montly.domain.TotalDataVO;
import com.apps.montly.domain.WeekVO;
import com.apps.montly.service.MonthlySvc;

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
		
		//log.debug("================get_monthly_usage==================");
		//log.debug(inVO.toString());
		//log.debug("===================================================");
		
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
		
		log.debug("=======================================usageList=============================================");
		log.debug(usageList.toString());
		log.debug("=============================================================================================");
		
		//날짜 List 생성
		List<WeekVO> weekList = (List<WeekVO>) monthlySvc.get_weekday(month);
		
		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
		count = 0;
		
		if(weekList != null && weekList.size()>0) {
			count = weekList.size();
		}
		
//		log.debug("weekList count : "+count);
//		log.debug("=======================WeekList===========================");
//		log.debug(weekList.toString());
//		log.debug("==========================================================");
		

		//for문 이용
		//1. weekList의 숫자 수정 : 1단위는 앞에 month + 0 붙여서 수정 ex ) 1 -> 20170901
		//1-2. 00단위라면 month 붙여서 수정 ex ) 10 -> 20170910
		//2. usageList와 날짜로 비교
		//3. Hashtable에 날짜, income, expense, total의 내용  put?
		//4. 다음 날짜로 넘어가면 list에 add
		//hashtable을 저장할 list
		List<TotalDataVO> monthlyList = new ArrayList<TotalDataVO>();
		
		//usage data 날짜 포맷 수정
		for(int i=0; i<usageList.size(); i++) {
			if(usageList.get(i).getDay() != null) {
				
				//날짜 자르기
				if(usageList.get(i).getDay().charAt(6) == '0') {
					usageList.get(i).setDay(usageList.get(i).getDay().substring(7, 8));
				} else {
					usageList.get(i).setDay(usageList.get(i).getDay().substring(6, 8));
				}
			}
		}
		
		//weekList 데이터 가공
		for(int i=0; i<weekList.size(); i++) {
			
			TotalDataVO[] monthlyDataVO = new TotalDataVO[7];
			
			for(int iniArr=0; iniArr<monthlyDataVO.length; iniArr++) {
				monthlyDataVO[iniArr] = new TotalDataVO();
			}
			
			//weekList에서 date, day 데이터 가공
			if( null == weekList.get(i).getSunday() || weekList.get(i).getSunday().equals("")) {
				monthlyDataVO[0].setDate("");
				monthlyDataVO[0].setDay("일");
			} else if ( null != weekList.get(i).getSunday()) {
				monthlyDataVO[0].setDate(weekList.get(i).getSunday());
				monthlyDataVO[0].setDay("일");
			}
			
			if( null == weekList.get(i).getMonday() || weekList.get(i).getMonday().equals("")) {
				monthlyDataVO[1].setDate("");
				monthlyDataVO[1].setDay("월");
			} else if ( null != weekList.get(i).getMonday()) {
				monthlyDataVO[1].setDate(weekList.get(i).getMonday());
				monthlyDataVO[1].setDay("월");
			}
			
			if( null == weekList.get(i).getTuesday() || weekList.get(i).getTuesday().equals("")) {
				monthlyDataVO[2].setDate("");
				monthlyDataVO[2].setDay("화");
			} else if ( null != weekList.get(i).getTuesday()) {
				monthlyDataVO[2].setDate(weekList.get(i).getTuesday());
				monthlyDataVO[2].setDay("화");
			}
			
			if( null == weekList.get(i).getWednesday() || weekList.get(i).getWednesday().equals("")) {
				monthlyDataVO[3].setDate("");
				monthlyDataVO[3].setDay("수");
			} else if ( null != weekList.get(i).getWednesday()) {
				monthlyDataVO[3].setDate(weekList.get(i).getWednesday());
				monthlyDataVO[3].setDay("수");
			}
			
			if( null == weekList.get(i).getThursday() || weekList.get(i).getThursday().equals("")) {
				monthlyDataVO[4].setDate("");
				monthlyDataVO[4].setDay("목");
			} else if ( null != weekList.get(i).getThursday()) {
				monthlyDataVO[4].setDate(weekList.get(i).getThursday());
				monthlyDataVO[4].setDay("목");
			}
			
			if( null == weekList.get(i).getFriday() || weekList.get(i).getFriday().equals("")) {
				monthlyDataVO[5].setDate("");
				monthlyDataVO[5].setDay("금");
			} else if ( null != weekList.get(i).getFriday()) {
				monthlyDataVO[5].setDate(weekList.get(i).getFriday());
				monthlyDataVO[5].setDay("금");
			}
			
			if( null == weekList.get(i).getSaturday() || weekList.get(i).getSaturday().equals("")) {
				monthlyDataVO[6].setDate("");
				monthlyDataVO[6].setDay("토");
			} else if ( null != weekList.get(i).getSaturday()) {
				monthlyDataVO[6].setDate(weekList.get(i).getSaturday());
				monthlyDataVO[6].setDay("토");
			}
			
			for(int num=0; num<monthlyDataVO.length; num++) {
				log.debug(monthlyDataVO[num].toString());
				monthlyList.add(monthlyDataVO[num]);
			}
		} //weekList for loop
		
		//monthlyList와 usageList비교하여  income, expense, totalUsage 등록
//		for(int i=0; i<usageList.size(); i++) {
//			for(int j=0; j<monthlyList.size(); j++) {
//				
//				//TODO : expense, income, totalUsage 값 제대로 안들어 오는 것 확인
//				if(null != monthlyList.get(j).getDate() || monthlyList.get(j).getDate().equals("")) {
//					if(null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("10")) {
//						monthlyList.get(j).setExpense(String.valueOf(usageList.get(i).getUsage()));
//					} else if(null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("20")) {
//						monthlyList.get(j).setIncome(String.valueOf(usageList.get(i).getUsage()));
//					} else if(null==usageList.get(i).getCategory() || usageList.get(i).getCategory().equals("")) {
//						monthlyList.get(j).setTotalUsage(String.valueOf(usageList.get(i).getUsage()));
//					}
//				} else if(null == monthlyList.get(j).getDate() || monthlyList.get(j).getDate().equals("")) {
//					monthlyList.get(j).setExpense("");
//					monthlyList.get(j).setIncome("");
//					monthlyList.get(j).setTotalUsage("");
//				}
//			}
//		}
		
		for(int i=0; i<usageList.size(); i++) {
			for(int j=0; j<monthlyList.size(); j++) {
				
				//income : o, expense : o, totalUsage : o
				if(null != monthlyList.get(j).getDate() || monthlyList.get(j).getDate().equals("")) {
					if(monthlyList.get(j).getDate().equals(usageList.get(i).getDay())) {
						if( null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("10")) {
							monthlyList.get(j).setExpense(String.valueOf(usageList.get(i).getUsage()));
						} else if( null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("20")) {
							monthlyList.get(j).setIncome(String.valueOf(usageList.get(i).getUsage()));
						} else if( null == usageList.get(i).getCategory() || usageList.get(i).getCategory().equals("")) {
							monthlyList.get(j).setTotalUsage(String.valueOf(usageList.get(i).getUsage()));
						}
					}
				} else {
					
				}
			}
		}
		
		log.debug(monthlyList.toString());
		
		//create ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("weekList", weekList);
		modelAndView.setViewName("monthly");
		
		return modelAndView;
	}
	
}
