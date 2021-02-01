package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("login")
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	
//	@RequestMapping("view")
	@RequestMapping(path="view", method = {RequestMethod.GET})
	public String view ( ) {
		
		logger.debug("login/view 시작");
		logger.debug("loginController.view()");
		
		return "login";
		
	}
	
	// post 메소드만 처리하도록 설정
	@RequestMapping(path="process", method = RequestMethod.POST )
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
