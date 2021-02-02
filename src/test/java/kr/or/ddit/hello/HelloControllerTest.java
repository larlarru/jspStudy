package kr.or.ddit.hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import kr.or.ddit.test.config.WebTestConfig;



public class HelloControllerTest extends WebTestConfig {
	
//	@Resource(name="helloController")
	
	// 스프링빈중에 대입 가능한 타입의 스프링 빈을 주입한다.
	// 만약 동일하 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	// 특정 스프링 빈의 이름을 지정할 수 있다.
	//	==> @Resource 이노테이션 하나를 사용 했을 때와 동일하다.
	
	
	@Test
	public void viewTest() throws Exception {
		
//		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
//										.andExpect(status().isOk()) // hello/view로 요청을 보냈을때 정상적인(?)응답(상태)을 기대한다 라는 의미이다.
//										.andExpect(view().name("hello"))
//										.andExpect(model().attributeExists("userVo"))
//										.andReturn();
//		ModelAndView mav = mvcResult.getModelAndView();
//		
//		assertEquals("hello", mav.getViewName());
//		
//		UserVo userVo = (UserVo) mav.getModel().get("userVo");
//		assertEquals("브라운", userVo.getUsernm());
//		
//		
		mockMvc.perform(get("/login/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
		
	}
	
	
	@Test
	public void pathVariableTest() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/brown"))
				.andExpect(status().isOk()) // hello/view로 요청을 보냈을때 정상적인(?)응답(상태)을 기대한다 라는 의미이다.
				.andExpect(model().attributeExists("subpath"))
				.andDo(print())
				.andReturn();
	}
	
	
//	@Test
//	public void processSuccessTest() throws Exception {
//		
//		mockMvc.perform(post("/login/process")
//							.param("userid", "brown")
//							.param("pass", "brownPass")
//							.param("price","1000"))
//				.andExpect(view().name("main"))
//				.andDo(print());
//		
//	}
//	
//	@Test
//	public void processFailTest() throws Exception {
//		
//		mockMvc.perform(post("/login/process")
//				.param("userid", "brown")
//				.param("pass", "failPass")
//				.param("price","1000"))
//		.andExpect(view().name("redirect:/login/view"))
//		.andDo(print());
//		
//	}
//	
	
	
	

}
