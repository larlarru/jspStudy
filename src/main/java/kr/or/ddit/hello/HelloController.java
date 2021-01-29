package kr.or.ddit.hello;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

@RequestMapping("hello")
@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	// localhost/hello/view ==> localhost/view(위의 requestMapping주석치게되면)
	// 그러면 localhost/hello/view하고 싶으면 @RequestMapping("hello/view") 이런식으로 추가한다.
	// localhost/hello/view.do
	@RequestMapping("view.do")
	public String view( Model model, HttpServletRequest request ) {
//	public String view( HttpServletRequest request ) {
//	public String view(Model model) {
		
		logger.debug("HelloController.view() {}", userService.getUser("brown"));
		
		request.setAttribute("userVo", userService.getUser("brown"));
//		model.addAttribute("userVo", userService.getUser("brown"));
		
		return "hello";
	}
	
}
	