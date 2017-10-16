package com.apps.daily.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.apps.daily.domain.DailyVO;
import com.apps.daily.service.DailySvc;
import com.google.gson.Gson;


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
	public ModelAndView daily(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws IOException {
		ModelAndView mav = new ModelAndView();
		DailyVO inVO = new DailyVO();
		if(session.getAttribute("ID") != null){
			inVO.setId(session.getAttribute("ID").toString());
			if(req.getParameter("reg_dt") != null){
				inVO.setReg_dt(req.getParameter("reg_dt"));
				mav.addObject("loadWork", "reload");
			}else{
				Date nowdate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				String today = sdf.format(nowdate);
				log.debug("now:"+nowdate.toString());
				log.debug(today);
				inVO.setReg_dt(today);
				mav.addObject("loadWork", "normal");
			}
			mav.addObject("reg_dt",inVO.getReg_dt());
			log.debug("------------------");
			log.debug("0: "+inVO);
			log.debug("------------------");
			List<DailyVO> list = (List<DailyVO>)dailySvc.do_search(inVO);
			log.debug("------------------");
			log.debug("3: "+ list);
			log.debug("------------------");
			int total_in = 0;
			int total_out = 0;
			int total_sum = 0;
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					int thisusage = list.get(i).getUsage();
					if(thisusage<0){
						total_out += (thisusage*-1);
					}else{
						total_in += thisusage;
					}
				}
				total_sum = Math.abs(total_in - total_out);
			}
			mav.addObject("total_in",total_in);
			mav.addObject("total_out",total_out);
			mav.addObject("total_sum",total_sum);
			mav.addObject("list",list );
			mav.setViewName("daily");
		}else{
			res.sendRedirect("../main.do");
			mav.setViewName("login");
		}	
		return mav;
	}
	
	@RequestMapping(value="budget/dailly.do",method=RequestMethod.POST) 
	@ResponseBody
	public String dailyAjax(HttpServletRequest req,HttpSession session) {

		
		String id = "id1";
		session.setAttribute("ID", id);
		DailyVO inVO = new DailyVO();
		inVO.setId(session.getAttribute("ID").toString());
		if(req.getParameter("reg_dt") != null){
			inVO.setReg_dt(req.getParameter("reg_dt"));
		}else{
			inVO.setReg_dt("");
		}
		log.debug("------------------");
		log.debug("0: "+inVO);
		log.debug("------------------");
		List<DailyVO> list = (List<DailyVO>)dailySvc.do_search(inVO);
		log.debug("------------------");
		log.debug("3: "+ list);
		log.debug("------------------");
		int total_in = 0;
		int total_out = 0;
		int total_sum = 0;
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				int thisusage = list.get(i).getUsage();
				if(thisusage<0){
					total_out += (thisusage*-1);
				}else{
					total_in += thisusage;
				}
			}
			total_sum = (total_in - total_out);
		}
		
		Gson gson=new Gson();
		String retString = gson.toJson(list);
		log.debug("4===============retString="+retString);
		
		return retString;
	}
	@RequestMapping(value="budget/up_save.do",method=RequestMethod.POST)
	public void do_save(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws IOException{
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
			log.debug("------------------");
			log.debug("0: "+inVO);
			log.debug("------------------");
			if(req.getParameter("workDiv").equals("update")){
				inVO.setDaily_code(req.getParameter("daily_code"));
				dailySvc.do_update(inVO);
				res.sendRedirect("daily.do?reg_dt="+inVO.getReg_dt());
			}else if(req.getParameter("workDiv").equals("save")){
				dailySvc.do_save(inVO);
				res.sendRedirect("daily.do?reg_dt="+inVO.getReg_dt());
			}
			
	}
	@RequestMapping(value="budget/do_save.do") 
	public ModelAndView input(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		log.debug("0=====================================");
		log.debug("save()");
		log.debug("0=====================================");
		mav.addObject("workDiv","save");
		mav.addObject("reg_dt",request.getParameter("reg_dt"));
		mav.addObject("daily_code",request.getParameter("daily_code"));
		mav.setViewName("save");
		return mav;
	}
	@RequestMapping(value="budget/do_searchOne.do",method=RequestMethod.POST)
	public ModelAndView do_searchOne(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws IOException{
			ModelAndView mav = new ModelAndView();
			DailyVO inVO = new DailyVO();
			inVO.setId(session.getAttribute("ID").toString());
			inVO.setDaily_code(req.getParameter("daily_code"));
			log.debug("------------------");
			log.debug("0: "+inVO);
			log.debug("------------------");
			mav.addObject("updateData", dailySvc.do_searchOne(inVO));
			log.debug("------------------");
			log.debug("3: "+ inVO);
			log.debug("------------------");
			mav.addObject("workDiv","update");
			mav.addObject("daily_code",req.getParameter("daily_code"));
			mav.setViewName("save");
			return mav;
	}
	@RequestMapping(value="budget/delete.do",method=RequestMethod.POST)
	public void delete(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws IOException{
			DailyVO inVO = new DailyVO();
			inVO.setId(session.getAttribute("ID").toString());
			inVO.setDaily_code(req.getParameter("daily_code"));
			log.debug("------------------");
			log.debug("0: "+inVO);
			log.debug("------------------");
				dailySvc.do_delete(inVO);
				res.sendRedirect("daily.do?reg_dt="+req.getParameter("reg_dt"));;
			
			
	}
	
	@RequestMapping(value="budget/cate.do",method=RequestMethod.POST) 
	@ResponseBody
	public String searchCate(HttpServletRequest req,HttpSession session) {
		
		DailyVO inVO = new DailyVO();
		inVO.setMst_ct_nm(req.getParameter("mst_ct_nm"));
		log.debug("------------------");
		log.debug("0: "+inVO);
		log.debug("------------------");
		List<DailyVO> list = (List<DailyVO>)dailySvc.category(inVO);
		log.debug("------------------");
		log.debug("3: "+ list);
		log.debug("------------------");
		
		Gson gson=new Gson();
		String retString = gson.toJson(list);
		log.debug("4===============retString="+retString);
		
		return retString;
	}
	
//	@RequestMapping(value="budget/daily.do")
//	public ModelAndView dailys_function(HttpServletRequest req,HttpServletResponse res,HttpSession session)throws IOException{
//		ModelAndView mav = new ModelAndView();
//		DailyVO inVO = new DailyVO();
//		if(session.getAttribute("ID") == null){
//			res.sendRedirect("../main.do");
//			mav.setViewName("login");
//		}else{
//			inVO.setId(session.getAttribute("ID").toString());
//			if(req.getParameter("workDiv") == "list"){
//				if(req.getParameter("reg_dt") != null){
//					inVO.setReg_dt(req.getParameter("reg_dt"));
//					mav.addObject("loadWork", "reload");
//				}else{
//					Date nowdate = new Date();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//					String today = sdf.format(nowdate);
//					log.debug("now:"+nowdate.toString());
//					log.debug(today);
//					inVO.setReg_dt(today);
//					mav.addObject("loadWork", "normal");
//				}
//				mav.addObject("reg_dt",inVO.getReg_dt());
//				log.debug("------------------");
//				log.debug("0: "+inVO);
//				log.debug("------------------");
//				List<DailyVO> list = (List<DailyVO>)dailySvc.do_search(inVO);
//				log.debug("------------------");
//				log.debug("3: "+ list);
//				log.debug("------------------");
//				int total_in = 0;
//				int total_out = 0;
//				int total_sum = 0;
//				if(list.size()>0){
//					for(int i=0;i<list.size();i++){
//						int thisusage = list.get(i).getUsage();
//						if(thisusage<0){
//							total_out += (thisusage*-1);
//						}else{
//							total_in += thisusage;
//						}
//					}
//					total_sum = Math.abs(total_in - total_out);
//				}
//				mav.addObject("total_in",total_in);
//				mav.addObject("total_out",total_out);
//				mav.addObject("total_sum",total_sum);
//				mav.addObject("list",list );
//				mav.setViewName("daily");
//				
//			}else if(req.getParameter("workDiv") == "update"){
//				
//			}else if(req.getParameter("workDiv") == "save"){
//				
//			}else if(req.getParameter("workDiv") == "delete"){
//				
//			}else{
//				if(req.getParameter("reg_dt") != null){
//					inVO.setReg_dt(req.getParameter("reg_dt"));
//					mav.addObject("loadWork", "reload");
//				}else{
//					Date nowdate = new Date();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//					String today = sdf.format(nowdate);
//					log.debug("now:"+nowdate.toString());
//					log.debug(today);
//					inVO.setReg_dt(today);
//					mav.addObject("loadWork", "normal");
//				}
//				mav.addObject("reg_dt",inVO.getReg_dt());
//				log.debug("------------------");
//				log.debug("0: "+inVO);
//				log.debug("------------------");
//				List<DailyVO> list = (List<DailyVO>)dailySvc.do_search(inVO);
//				log.debug("------------------");
//				log.debug("3: "+ list);
//				log.debug("------------------");
//				int total_in = 0;
//				int total_out = 0;
//				int total_sum = 0;
//				if(list.size()>0){
//					for(int i=0;i<list.size();i++){
//						int thisusage = list.get(i).getUsage();
//						if(thisusage<0){
//							total_out += (thisusage*-1);
//						}else{
//							total_in += thisusage;
//						}
//					}
//					total_sum = Math.abs(total_in - total_out);
//				}
//				mav.addObject("total_in",total_in);
//				mav.addObject("total_out",total_out);
//				mav.addObject("total_sum",total_sum);
//				mav.addObject("list",list );
//				mav.setViewName("daily");
//			}
//
//			
//			
//			
//		}//sessionCheck
//		
//		return mav;
//		
//		
//	}
}
