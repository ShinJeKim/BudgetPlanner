package com.apps.user;

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
public class UserTest {
	
	private Logger log = LoggerFactory.getLogger(UserTest.class);
	
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
	
	/**
	 *  COLUMN NAME		TYPE
	 *  ---------------------------------
	 *  ID				VARCHAR2(12 BYTE)
	 *  PASSWORD		VARCHAR2(12 BYTE)
	 *  NAME			VARCHAR2(30 CHAR)
	 *  EMAIL			VARCHAR2(50 BYTE)
	 *  FIXED_INCOME	NUMBER(10,0)
	 *  BALANCE			NUMBER(12,0)
	 *  REG_DT			DATE
	 *  MOD_DT			DATE
	 *  DELETE_FLAG		NUMBER(1,0)
	 *  ADMIN_FLAG		NUMBER(1,0)
	 */
	
	//@Test
	public void do_check_id() throws Exception{
		MockHttpServletRequestBuilder createMessage = 
				post("/user/do_check_id.do")
				.param("id", "Testcase");
		
		mockMvc.perform(createMessage).andDo(print())
		.andExpect(status().is(0));
	}
	
	@Test
	public void do_save() throws Exception{
		MockHttpServletRequestBuilder createMessage = 
				post("/user/do_save.do")
				.param("id", "Testcase")
				.param("password", "1234")
				.param("name", "Testcase")
				.param("email", "testcase@testcase.com")
				.param("fixed_income", "6512345")
				.param("balance", "561233345");
	
		mockMvc.perform(createMessage).andDo(print())
		.andExpect(status().is2xxSuccessful());
		
	}
	
	
}
