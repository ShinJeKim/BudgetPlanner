package com.apps.montly.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.apps.montly.domain.MonthlyVO;
import com.apps.montly.domain.TotalDataVO;
import com.apps.montly.domain.WeekVO;
import com.apps.montly.service.MonthlySvc;
import com.apps.user.domain.UserVO;
import com.google.gson.Gson;

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
	public ModelAndView monthly(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		String id = "";
		String month = "";
		
		//Session에 값이 들어있을 경우 ID 할당
		if(session.getAttribute("ID") != null) {
			id = session.getAttribute("ID").toString();
		} else {
			//없을 경우 main으로 redirect
			response.sendRedirect("../main.do");
			modelAndView.setViewName("login");
		}
		
		if(request.getParameter("month") != null) {
			month = request.getParameter("month");
			modelAndView.addObject("month", month);
		} else {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			month = sdf.format(now);
			modelAndView.addObject("month", month);
		}

		//월별 사용 내역 List 생성
		List<MonthlyVO> usageList = (List<MonthlyVO>) monthlySvc.get_monthly_usage(id, month);
		
		//날짜 List 생성
		List<WeekVO> weekList = (List<WeekVO>) monthlySvc.get_weekday(month);
		
		//usageList와 weekList를 가공하여 저장할 List 생성
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
		
		int absValue = 0;
		
		//income, expense, total 금액 분류 후 맵핑
		for(int i=0; i<usageList.size(); i++) {
			for(int j=0; j<monthlyList.size(); j++) {
				
				//income : o, expense : o, totalUsage : o
				if(null != monthlyList.get(j).getDate() || monthlyList.get(j).getDate().equals("")) {
					if(monthlyList.get(j).getDate().equals(usageList.get(i).getDay())) {
						if( null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("10")) {
							
							//음수 절대값 처리
							absValue = Math.abs(usageList.get(i).getUsage());
							monthlyList.get(j).setExpense(String.valueOf(absValue));
							
						} else if( null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("20")) {
							monthlyList.get(j).setIncome(String.valueOf(usageList.get(i).getUsage()));
						} else if( null == usageList.get(i).getCategory() || usageList.get(i).getCategory().equals("")) {
								monthlyList.get(j).setTotalUsage(String.valueOf(usageList.get(i).getUsage()));
						}
					}
				} else {
					//전체 total 구할필요 없음
				}
			}
		}
		
		int monthlyListSize = monthlyList.size();
		
		log.debug(monthlyList.toString());
		
		modelAndView.addObject("monthlyList", monthlyList);
		modelAndView.addObject("monthlyListSize", monthlyListSize);
		modelAndView.setViewName("monthly");
		
		return modelAndView;		
	}
	
	//get_monthly_usage
	@RequestMapping(value="budget/get_monthly_usage.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView get_monthly_usage(HttpServletRequest request, HttpSession session) {
		
		log.debug("=============================get_monthly_usage============================");
		
		//create ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		
		//id, month setting
		String id = session.getAttribute("ID").toString();
		String month = "";
		if(request.getParameter("month") != null) {
			month = request.getParameter("month");
			modelAndView.addObject("month", month);
		} else {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			month = sdf.format(now);
		}
		
		//TestCase
		//String id = "id2";
		//String month = "201709";
		
		//월별 사용 내역 List 생성
		List<MonthlyVO> usageList = (List<MonthlyVO>) monthlySvc.get_monthly_usage(id, month);
		
		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
		int count = 0;
		
//		if(usageList != null && usageList.size()>0) {
//			count = usageList.size();
//		}
//		
//		log.debug("=======================================usageList=============================================");
//		log.debug(usageList.toString());
//		log.debug("=============================================================================================");
		
		//날짜 List 생성
		List<WeekVO> weekList = (List<WeekVO>) monthlySvc.get_weekday(month);
		
//		//데이터가 잘 들어오는지 확인하기 위한 변수 선언
//		count = 0;
//		
//		if(weekList != null && weekList.size()>0) {
//			count = weekList.size();
//		}
		
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
		
		int absValue = 0;
		
		//income, expense, total 금액 분류 후 맵핑
		for(int i=0; i<usageList.size(); i++) {
			for(int j=0; j<monthlyList.size(); j++) {
				
				//income : o, expense : o, totalUsage : o
				if(null != monthlyList.get(j).getDate() || monthlyList.get(j).getDate().equals("")) {
					if(monthlyList.get(j).getDate().equals(usageList.get(i).getDay())) {
						if( null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("10")) {
							
							//음수 절대값 처리
							absValue = Math.abs(usageList.get(i).getUsage());
							monthlyList.get(j).setExpense(String.valueOf(absValue));
							
						} else if( null != usageList.get(i).getCategory() && usageList.get(i).getCategory().equals("20")) {
							monthlyList.get(j).setIncome(String.valueOf(usageList.get(i).getUsage()));
						} else if( null == usageList.get(i).getCategory() || usageList.get(i).getCategory().equals("")) {
								monthlyList.get(j).setTotalUsage(String.valueOf(usageList.get(i).getUsage()));
						}
					}
				} else {
					//전체 total 구할필요 없음
				}
			}
		}
		
		log.debug(monthlyList.toString());
		
		int monthlyListSize = monthlyList.size();
		
		modelAndView.addObject("monthlyList", monthlyList);
		modelAndView.addObject("monthlyListSize", monthlyListSize);
		modelAndView.setViewName("monthly");
		
//		Gson gson = new Gson();
//		String resultStr = gson.toJson(monthlyList);
		
		return modelAndView;
	}
	
}
