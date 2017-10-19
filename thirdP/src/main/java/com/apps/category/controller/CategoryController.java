package com.apps.category.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.apps.category.domain.CategoryVO;
import com.apps.category.service.CategorySvc;
import com.apps.common.DTO;
import com.apps.common.StringUtil;
import com.apps.daily.domain.DailyVO;
import com.google.gson.Gson;

@Controller
public class CategoryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategorySvc catSvc;
	
	@Resource(name="downloadView")
	private View downloadView;
	
	//헤더없는 페이지
	@RequestMapping(value="org/category.do") 
	public String orgcategory(HttpServletRequest request) {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		return "items/budget/category";
	}
	
	//헤더적용 페이지
	@RequestMapping(value="budget/category.do") 
	public String category(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		
		log.debug("0=====================================");
		log.debug("main()");
		log.debug("0=====================================");
		
		if(session.getAttribute("ID") == null) {
			response.sendRedirect("../main.do");
		}
		
		return "category";
	}
	
	@RequestMapping(value="budget/do_searchCategory.do", method=RequestMethod.GET
			)
	@ResponseBody
	public String do_searchCategory(HttpServletRequest req){
		log.debug("=================================");
		log.debug("do_searchCategory.do");
		log.debug("=================================");
		
		String mst_ct_id = StringUtil.nvl(req.getParameter("mst_ct_id"),"");
		log.debug("mst_ct_id: "+mst_ct_id);
		List<String> dtlList = catSvc.do_searchCategory(mst_ct_id);
		
		Gson gson = new Gson();
		String dtl_ct_id = gson.toJson(dtlList);
		log.debug("dtl_ct_id: "+dtl_ct_id);
		
		return dtl_ct_id;
	}
	
	
	@RequestMapping(value="budget/do_searchList.do", method=RequestMethod.POST
			, produces="application/json;charset=utf8")
	@ResponseBody
	public String do_searchList(HttpServletRequest req,HttpServletResponse response, HttpSession session) throws IOException, NumberFormatException{
		log.debug("=================================");
		log.debug("do_searchList.do");
		log.debug("=================================");
		ModelAndView modelAndView = new ModelAndView();
		CategoryVO catVO = new CategoryVO();
		Hashtable<String, String> searchParam = new Hashtable<String, String>();
		
		String id = "";
		
		//Session에 값이 들어있을 경우 ID 할당
		if(session.getAttribute("ID") != null) {
			id = session.getAttribute("ID").toString();
		} else {
			//없을 경우 main으로 redirect
			response.sendRedirect("../main.do");
			modelAndView.setViewName("login");
		}
		
		catVO.setId(session.getAttribute("ID").toString());

		String pageSize = StringUtil.nvl(req.getParameter("page_size"), "10");
		String pageNum = StringUtil.nvl(req.getParameter("page_num"), "1");
		String start_date = StringUtil.nvl(req.getParameter("start_date"), "2017-08-01");
		String end_date = StringUtil.nvl(req.getParameter("end_date"), "2017-09-22");
		String mst_ct_id = StringUtil.nvl(req.getParameter("mst_ct_id"), "");
		String dtl_ct_nm = StringUtil.nvl(req.getParameter("dtl_ct_nm"), "");
		
		searchParam.put("id".toString(), id);
		searchParam.put("page_size".toString(), pageSize);
		searchParam.put("page_num".toString(), pageNum);
		searchParam.put("start_date".toString(), start_date);
		searchParam.put("end_date".toString(), end_date);
		searchParam.put("mst_ct_id".toString(), mst_ct_id);
		searchParam.put("dtl_ct_nm".toString(), dtl_ct_nm);

		log.debug("searchParam: "+searchParam);

		// request 이름 read
		Enumeration<String> params = req.getParameterNames();
		Hashtable<String, String> sParam = new Hashtable<String, String>();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			req.getParameter(name);
			sParam.put(name, StringUtil.nvl(req.getParameter(name), ""));
		}

		catVO.setParam(searchParam);

		List<DailyVO> list = catSvc.do_searchList(catVO);
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(0);
		nf.setMaximumIntegerDigits(8);
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setUsage(nf.format(Integer.parseInt(list.get(i).getUsage())));
		}
		
		Gson gson = new Gson();
		String searchList = gson.toJson(list);
		log.debug("do_searchList: "+searchList);
		return searchList;
	}
	
	
	/**
	 * excelDownload
	 * @param req
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="budget/do_excelDown.do", method=RequestMethod.POST)
	public ModelAndView excelDownload(HttpServletRequest req, HttpSession session) throws IOException{
		
		log.debug("=================================");
		log.debug("do_excelDown.do");
		log.debug("=================================");
		
		CategoryVO catVO = new CategoryVO();
		Hashtable<String, String> searchParam = new Hashtable<String, String>();
		ModelAndView modelAndView = new ModelAndView();
		
		String id = session.getAttribute("ID").toString();

		log.debug("start_date" + req.getParameter("start_date"));

		String start_date = StringUtil.nvl(req.getParameter("start_date"), "");
		String end_date = StringUtil.nvl(req.getParameter("end_date"), "");
		String mst_ct_id = StringUtil.nvl(req.getParameter("mst_ct_id"), "");
		String dtl_ct_nm = StringUtil.nvl(req.getParameter("dtl_ct_nm"), "");
		
		searchParam.put("id".toString(), id);
		searchParam.put("start_date".toString(), start_date);
		searchParam.put("end_date".toString(), end_date);
		searchParam.put("mst_ct_id".toString(), mst_ct_id);
		searchParam.put("dtl_ct_nm".toString(), dtl_ct_nm);
		
		
		
		catVO.setParam(searchParam);
		log.debug("catVO==============="+catVO.toString());
		
		List<DailyVO> list = catSvc.do_searchExcel(catVO);

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(0);
		nf.setMaximumIntegerDigits(8);
		
		for(int i=0; i<list.size(); i++) {
			list.get(i).setUsage(nf.format(Integer.parseInt(list.get(i).getUsage())));
			list.get(i).setContent(list.get(i).getContent().replaceAll("<p>|</p>", ""));
			list.get(i).setContent(list.get(i).getContent().replaceAll("<br>", "\n"));
		}
		
		String fileFullPath = this.catSvc.do_excelDown(list);
		
		
		log.debug("===========================");
		log.debug("fileFullPath: "+fileFullPath);
		log.debug("===========================");
		modelAndView.setView(this.downloadView);
		File downloadFile = new File(fileFullPath);
		modelAndView.addObject("downloadFile", downloadFile);
		
		return modelAndView;
	}

}
