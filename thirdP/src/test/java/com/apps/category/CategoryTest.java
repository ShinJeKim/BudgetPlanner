package com.apps.category;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class CategoryTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		log.debug("===================================");
		log.debug("ctx: "+ctx.toString());
		log.debug("===================================");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		log.info("===================================");
		log.info("mockMvc: "+mockMvc.toString());
		log.info("===================================");
	}
	
	@Test
	public void setSetUp() {
		log.debug("=================================");
		log.debug("ctx: "+ctx.toString());
		log.debug("=================================");
	}
	
	//@Test
	public void do_searchCategory() throws Exception{
		MockHttpServletRequestBuilder createMessage
												= get("/budget/do_searchCategory.do")
														.param("mst_ct_id", "10");
			mockMvc.perform(createMessage)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(status().is2xxSuccessful());
		
	}
	
	@Test
	public void do_searchList() throws Exception{
		MockHttpServletRequestBuilder createMessage
												= post("/budget/do_searchList.do")
														.param("id", "id1")
														.param("page_size", "20")
														.param("page_num", "1")
														.param("start_date", "2017-10-01")
														.param("end_date", "2017-11-01")
														.param("mst_ct_id", "20")
														.param("dtl_ct_nm", "전체");
			mockMvc.perform(createMessage)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(status().is2xxSuccessful());
	}
	
	
}

















