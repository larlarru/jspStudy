package kr.or.ddit.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;

public class UserControllerTest extends WebTestConfig{
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	
	@Test
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser"))
				.andExpect(view().name("user/allUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andDo(print());
	}
	
	@Test
	public void pagingUserTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser") )
				.andExpect(view().name("user/pagingUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVo"))
				.andExpect(model().attributeExists("pagination"))
				.andDo(print());
	}
	
	@Test
	public void pagingUser2PageTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser").param("page", "2") )
				.andExpect(view().name("user/pagingUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVo"))
				.andExpect(model().attributeExists("pagination"))
				.andDo(print());
	}
	
	@Test
	public void registUserPageTest() throws Exception {
		mockMvc.perform(get("/user/registUserPage") )
				.andExpect(view().name("user/registUser"))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
//	@Test
//	public void registUserTest() throws Exception {
//
//		MockMultipartFile file = new MockMultipartFile("picture", "sally.png", "image/png", new byte[512]);
//		
//		mockMvc.perform(get("/user/registUser")
//				.file("profile", file) 
//				.param("userid", "brown_test") 
//				.param("usernm", "brown_test") 
//				.param("pass", "1234") 
//				.param("reg_dt", "") 
//				.param("alias", "brown_test") 
//				.param("addr1", "") 
//				.param("addr2", "") 
//				.param("zipcode", "") 
//				.param("filename", "") 
//				.param("realfilename", "") 
//				.param("s_userid", "brown") 
//				)
//		.andExpect(view().name("login/process"))
//		.andExpect(status().isOk())
//		.andDo(print());
//	}
//	
//	@Test
//	public void deleteUserTest() throws Exception {
//		mockMvc.perform(get("/user/deleteUser")
//				.param("s_userid", "brown") 
//				.param("userid", "brown_test") 
//				)
//		.andExpect(view().name("login/main"))
//		.andExpect(status().isOk())
//		.andDo(print());
//	}
//	
	@Test
	public void userTest() throws Exception {
		mockMvc.perform(get("/user/user")
				.param("userid", "brown") 
				)
		.andExpect(view().name("user/user"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void modifyUserTest() throws Exception {
		mockMvc.perform(get("/user/modifyUser")
				.param("userid", "brown_test") 
				.param("usernm", "brown_test") 
				.param("pass", "1234") 
				.param("reg_dt", "") 
				.param("alias", "brown_test") 
				.param("addr1", "") 
				.param("addr2", "") 
				.param("zipcode", "") 
				.param("filename", "") 
				.param("realfilename", "") 
				.param("s_userid", "brown") 
				)
		.andExpect(view().name("main"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	
}
