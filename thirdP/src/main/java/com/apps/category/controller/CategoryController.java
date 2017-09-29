package com.apps.category.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.apps.category.domain.CategoryVO;
import com.apps.category.service.CategorySvc;
import com.apps.common.DTO;
import com.apps.common.StringUtil;

@Controller
public class CategoryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategorySvc catSvc;

	
	@RequestMapping(value="items/do_searchCategory.do", method=RequestMethod.GET
			)
	public ModelAndView do_searchCategory(HttpServletRequest req){
		log.debug("=================================");

		log.debug("do_searchCategory.do");
		log.debug("=================================");
		String mstCtId = StringUtil.nvl(req.getParameter("mst_ct_id"),"");
		List<String> catList = catSvc.do_searchCategory(mstCtId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("catList", catList);
		modelAndView.setViewName("items/category");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "items/do_searchOne.do", method=RequestMethod.GET)
	public ModelAndView do_searchOne(HttpServletRequest req) {
		log.debug("=================================");
		log.debug("do_searchOne.do");
		log.debug("=================================");
		CategoryVO catVO = new CategoryVO();
		Hashtable<String, String> searchParam = new Hashtable<String, String>();
		
		String id = StringUtil.nvl(req.getParameter("id"), "");
		String pageSize = StringUtil.nvl(req.getParameter("page_size"), "");
		String pageNum = StringUtil.nvl(req.getParameter("page_num"), "");
		String start_date = StringUtil.nvl(req.getParameter("start_date"), "");
		String end_date = StringUtil.nvl(req.getParameter("end_date"), "");
		String mst_ct_id = StringUtil.nvl(req.getParameter("mst_ct_id"), "");
		String dtl_ct_id = StringUtil.nvl(req.getParameter("dtl_ct_id"), "");
		
		searchParam.put("id".toString(), id);
		searchParam.put("page_size".toString(), pageSize);
		searchParam.put("page_num".toString(), pageNum);
		searchParam.put("start_date".toString(), start_date);
		searchParam.put("end_date".toString(), end_date);
		searchParam.put("mst_ct_id".toString(), mst_ct_id);
		searchParam.put("dtl_ct_id".toString(), dtl_ct_id);

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
		
		List<CategoryVO> list = catSvc.do_searchList(catVO);
		int totalCnt = 0;
		if(list != null && list.size()>0)
			totalCnt = list.get(0).getTotalNo();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", catSvc.do_searchList(catVO));
		modelAndView.addObject("totalCnt", totalCnt);
		modelAndView.setViewName("items/category");
		
		return modelAndView;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
