package kr.or.ddit.login;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.test.config.WebTestConfig;

@RequestMapping("login")
public class LoginControllerTest extends WebTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);
	
	@Test
	public void viewtest() throws Exception {
		
		// localhost/login/view
		mockMvc.perform(get("/login/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
	
	@Test
	public void processSuccessTest() throws Exception {
		
		mockMvc.perform(post("/login/process")
							.param("userid", "brown")
							.param("pass", "brownPass")
							.param("price","1000"))
				.andExpect(view().name("main"))
				.andDo(print());
		
	}
	
	@Test
	public void processFailTest() throws Exception {
		
		mockMvc.perform(post("/login/process")
				.param("userid", "brown")
				.param("pass", "failPass")
				.param("price","1000"))
		.andExpect(view().name("redirect:/login/view"))
		.andDo(print());
		
	}
	

}
