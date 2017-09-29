package com.apps.category.controller;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apps.category.domain.CategoryVO;
import com.apps.category.service.CategorySvc;
import com.apps.common.StringUtil;

@Controller
public class CategoryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategorySvc catSvc;

	
	@RequestMapping(value = "items/category.do")
	public ModelAndView do_searchOne(HttpServletRequest req) {

		CategoryVO catVO = new CategoryVO();
		Hashtable<String, String> searchParam = new Hashtable<String, String>();

		String pageSize = StringUtil.nvl(req.getParameter("page_size").toString(), "10");
		String pageNum = StringUtil.nvl(req.getParameter("page_num").toString(), "1");
		String searchWord = StringUtil.nvl(req.getParameter("search_word").toString(), "");
		String searchDiv = StringUtil.nvl(req.getParameter("search_div").toString(), "");

		searchParam.put("page_size".toString(), pageSize);
		searchParam.put("page_num".toString(), pageNum);
		searchParam.put("search_word".toString(), searchWord);
		searchParam.put("search_div".toString(), searchDiv);

		// request 이름 read
		Enumeration<String> params = req.getParameterNames();
		Hashtable<String, String> sParam = new Hashtable<String, String>();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			req.getParameter(name);
			sParam.put(name, StringUtil.nvl(req.getParameter(name), ""));
		}

		catVO.setParam(searchParam);
		
		List<CategoryVO> list = catSvc.do_searchOne(catVO);
		int totalCnt = 0;
		if(list != null && list.size()>0)
			totalCnt = list.get(0).getTotalNo();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", catSvc.do_searchOne(catVO));
		modelAndView.addObject("totalCnt", totalCnt);
		modelAndView.setViewName("items/category");
		
		return modelAndView;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
