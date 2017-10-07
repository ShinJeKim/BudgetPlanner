package com.apps.daily.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.apps.daily.domain.DailyVO;
import com.apps.daily.service.DailySvc;


@Controller
public class DailyController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DailySvc dailySvc;
	
	//헤더없는 페이지
	@RequestMapping(value="org/daily.do") 
	public String orgdaily(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "items/budget/daily";
	}
	
	//헤더적용 페이지
	@RequestMapping(value="budget/daily.do") 
	public ModelAndView daily(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		mav.setViewName("daily");
		return mav;
	}
	@RequestMapping(value="budget/do_save.do",method=RequestMethod.POST)
	public void do_save(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws IOException{
			//ModelAndView mav = new ModelAndView();
			DailyVO inVO = new DailyVO();
			inVO.setId(session.getAttribute("ID").toString());
			inVO.setMst_ct_nm(req.getParameter("mst_ct_nm"));
			inVO.setDtl_ct_nm(req.getParameter("dtl_ct_nm"));
			inVO.setContent(req.getParameter("content"));
			inVO.setReg_dt(req.getParameter("reg_dt"));
			if(req.getParameter("mst_ct_nm").equals("지출")){
				inVO.setUsage(Integer.parseInt(req.getParameter("usage"))*-1);
			}else if(req.getParameter("mst_ct_nm").equals("수입")){
				inVO.setUsage(Integer.parseInt(req.getParameter("usage")));
			}
			
			dailySvc.do_save(inVO);	
			res.sendRedirect("daily.do");
//			mav.setViewName("daily");
//			return mav;
	}
	
	
}
