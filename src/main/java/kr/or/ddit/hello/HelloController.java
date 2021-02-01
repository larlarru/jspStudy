package kr.or.ddit.hello;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("hello")
@Controller
public class HelloController  {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	// localhost/hello/view ==> localhost/view(위의 requestMapping주석치게되면)
	// 그러면 localhost/hello/view하고 싶으면 @RequestMapping("hello/view") 이런식으로 추가한다.
	// localhost/hello/view.do
//	@RequestMapping("view.do")
	@RequestMapping("view")
	public String view( Model model, HttpServletRequest request ) {
		
		logger.debug("loginController.view()");
//		logger.debug("HelloController.view() {}", userService.getUser("brown"));
		
//		request.setAttribute("userVo", userService.getUser("brown"));
//		model.addAttribute("userVo", userService.getUser("brown"));
		
		return "login";
	}
	
//	@RequestMapping("process")
//	public String process(String userid, String pass, int price){
//		
//		logger.debug("userid {}", userid);
//		logger.debug("pass {}", pass);
//		logger.debug("price {}", price);
//		
//		return "";
//		
//	}
//	
//	@RequestMapping("process")
	public String process(UserVo userVo, HttpSession session) {
		
		logger.debug("userVo : {}", userVo);
		
		UserVo dbUser = userService.getUser(userVo.getUserid());
		
		logger.debug("HelloController.view() dbUser {}", dbUser);
		
		logger.debug("process의 if 조건 시작");
		
		if(dbUser != null && userVo.getPass().equals(dbUser.getPass())) {
			
			logger.debug("if조건 통과");
			session.setAttribute("S_USER", dbUser);
			return "main";
		}
		else {
			logger.debug("if조건 통과 실패");
			return "redirect:/login/view";
		}
		
	}
	
}
	