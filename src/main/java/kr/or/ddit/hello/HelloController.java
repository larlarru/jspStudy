package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@SessionAttributes("rangers")
@RequestMapping("hello")
@Controller
public class HelloController  {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@ModelAttribute(name = "rangers")
	
	public List<String> rangers() {
		
		logger.debug("helloController.ranger()");
		
		List<String> list = new ArrayList<String>();
		
		list.add("brown");
		list.add("sally");
		list.add("james");
		list.add("cony");
		list.add("moon");
		
		return list;
	}
	
	// localhost/hello/view ==> localhost/view(위의 requestMapping주석치게되면)
	// 그러면 localhost/hello/view하고 싶으면 @RequestMapping("hello/view") 이런식으로 추가한다.
	// localhost/hello/view.do
//	@RequestMapping("view.do")
	@RequestMapping("view")
	public String view( Model model, 
			@ModelAttribute(name="rangers") List<String> rangers , 
			HttpServletRequest request ) {
		
		logger.debug("loginController.view()");
//		logger.debug("HelloController.view() {}", userService.getUser("brown"));
		
		logger.debug("rangers : {}", rangers);
		
//		request.setAttribute("userVo", userService.getUser("brown"));
//		model.addAttribute("userVo", userService.getUser("brown"));
		
		return "hello";
//		return "login";
	}
	
	// hello /path/subpath
	
	// hello /path/brown
	// hello /path/cony
	// hello /path/moon
	@RequestMapping("path/{subpath}")
	public String pathVariable( @PathVariable("subpath") String subpath, Model model,
			/* User-Agent 해더 값 바인딩 */ @RequestHeader(name="User-Agent") String userAgent ) {
		
		
		// User-Agent 값 로거로 출력
		logger.debug("User-Agent",userAgent);
		
		
		model.addAttribute("subpath", subpath);
		
		return "hello";
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
	