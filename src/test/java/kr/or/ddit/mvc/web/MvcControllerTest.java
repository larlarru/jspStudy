package kr.or.ddit.mvc.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.config.WebTestConfig;

public class MvcControllerTest extends WebTestConfig {

	
	@Test
	public void viewTest() throws Exception {
		
		mockMvc.perform(get("/mvc/fileupload/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("file/view"))
				.andDo(print());
		
	}
	
	@Test
	public void fileuploadViewTest() throws Exception {
		
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/sally.png");
		
		MockMultipartFile file = new MockMultipartFile("picture", "sally.png", "image/png", new byte[512]);
		
		mockMvc.perform(fileUpload("/mvc/fileupload/upload")
								.file(file)
								.param("userid", "brown"))
				.andExpect(view().name("file/view"))
				.andDo(print());
		
						
	}

}
