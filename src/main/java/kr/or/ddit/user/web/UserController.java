package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping("allUser")
	public String allUser(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());

		return "user/allUser";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam( defaultValue = "1") int page, 
								@RequestParam( defaultValue = "5" )int pageSize,
								Model model) {
//								@RequestParam( name="p" ) int price ) {

		
		PageVo pageVo = new PageVo(page, pageSize);
		
		
//		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
//		model.addAllAttributes(map);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
//		int userCnt = (int)map.get("userCnt");
//		int pagination = (int)Math.ceil((double)userCnt / pageSize);
//				
//		model.addAttribute("userList", map.get("userList"));
//		model.addAttribute("pageVo", pageVo);
//		model.addAttribute("pagination", pagination);
		
		return "user/pagingUser";
	}
	
//	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {
		
		logger.debug("pageVo : {}", pageVo);
		
		return "";
	}
	
	@RequestMapping("user")
	public String user(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));

		return "user/user";
	}
	
	@RequestMapping("registUserPage")
	public String registUserPage() {
		return "user/registUser";
	}
	
	@RequestMapping("registUser")
	public String registUser(String s_userid, UserVo userVo, MultipartFile profile,  HttpSession session ) {
		
		logger.debug("s_userid {}, userVo {}, profile {}", s_userid, userVo, profile);
		
		try {
			
			profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			
		} catch ( IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		userVo.setFilename(profile.getOriginalFilename());
		userVo.setRealfilename(profile.getOriginalFilename());
		
		int registCnt = userService.registUser(userVo);
		
		
		if (registCnt == 1) {
			

			UserVo dbUser = userService.getUser(userVo.getUserid());
			session.setAttribute("S_USER", dbUser);
			return "main";
		}
		else return "user/registUserPage";
		
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(String s_userid, String userid,  HttpSession session ) {
		
		int registCnt = userService.deleteUser(userid);
		
		
		if (registCnt == 1 && s_userid != null) {
			
			session.removeAttribute("S_USER");
			session.setAttribute("S_USER", userService.getUser(s_userid));
			return "main";
		}
		else return "main";
		
	}
	
	
	@RequestMapping("modifyUserPage")
	public String modifyUserPage(String userid, Model model) {
		
		logger.debug("userid :"+userid);
		
		model.addAttribute("user", userService.selectUser(userid));

		return "user/modifyUser";
	}
	
	@RequestMapping("modifyUser")
	public String modifyUser(String s_userid, UserVo userVo, MultipartFile profile,  HttpSession session) {
		
		logger.debug("s_userid {}, userVo {}, profile {}", s_userid, userVo, profile);
		
		try {
			
			profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			
		} catch ( IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		userVo.setFilename(profile.getOriginalFilename());
		userVo.setRealfilename(profile.getOriginalFilename());
		
		int modifyCnt = userService.modifyUser(userVo);
		
		
		if (modifyCnt == 1 && s_userid != null) {
			
			session.removeAttribute("S_USER");
			session.setAttribute("S_USER", userService.getUser(s_userid));
			return "main";
		}
		else return "user/modifyUserPage";
		
	}
	
//	// 파라미터는 : userid, picture
//		@RequestMapping("fileupload/upload")
//		public String fileupload(String userid, MultipartFile picture) {
//			
//			logger.debug("userid : {}", userid);
//			logger.debug("filesize : {}, name : {} , originalFilename : {}",
//					picture.getSize(), picture.getName(), picture.getOriginalFilename());
//			
//			try {
//				
//				picture.transferTo(new File("d:\\upload\\" + picture.getOriginalFilename()));
//				
//			} catch ( IllegalStateException | IOException e) {
//				e.printStackTrace();
//			}
//			
//			return "file/view";
//		}
	
	
}
