package com.apps.monthly;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
	"file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml",
	"file:src\\main\\webapp\\WEB-INF\\spring\\appServlet\\servlet-context.xml"
})
@WebAppConfiguration
public class MonthlyTest {

	private Logger log = LoggerFactory.getLogger(MonthlyTest.class);
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		log.debug("========================");
		log.debug("ctx : "+ctx.toString());
		log.debug("========================");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		log.info("========================");
		log.info("mockMvc : "+mockMvc.toString());
		log.info("========================");
	}
	
	@Test
	public void get_monthly_usage() throws Exception{
		MockHttpServletRequestBuilder createMessage =
				post("/monthly/get_monthly_usage.do")
				.param("id", "id2")
				.param("month", "201709");
		
		mockMvc.perform(createMessage).andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void get_weekday() throws Exception{
		MockHttpServletRequestBuilder createMessage =
				post("/monthly/get_weekday.do")
				.param("month", "201709");
		
		mockMvc.perform(createMessage).andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
}
