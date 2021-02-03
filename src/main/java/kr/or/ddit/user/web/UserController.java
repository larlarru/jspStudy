package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;

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
	
	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());

		return "tiles.user.allUser";
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
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam( defaultValue = "1") int page, 
								@RequestParam( defaultValue = "5" )int pageSize,
								Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		// tiles-definition에 설정한 name
		return "tiles.user.pagingUser";
	}
	
	
//	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {
		
		logger.debug("pageVo : {}", pageVo);
		
		return "";
	}
	
	@RequestMapping("userTiles")
	public String userTiles(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));

		return "tiles.user.user";
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
	public String registUser(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model ) {
		
		// 
		// 
		
		
		// UserVo userVo, MutipartFIle profile,Model model 나중에 수정하기 
		// BindingResult result 이거는 추가됨 그리고 순서도 중요한다. 그리고 항상 해당 객체 뒤에? 선언해야한다.
		
//		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			
			logger.debug("result has error");
			
//			return "redirect:/user/registUserPage";	// 서블릿?경로
			return "user/registUser";	// <- jsp 겨올
			// jsp로 돌아간다
		}
		
		logger.debug("userVo {}, profile {}", userVo, profile);
		
		try {
			
			profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			
		} catch ( IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		userVo.setFilename(profile.getOriginalFilename());
		userVo.setRealfilename(profile.getOriginalFilename());
		
		int registCnt = userService.registUser(userVo);
		
		
		if (registCnt == 1) {
			

			return "main";

		}
		else return "user/registUserPage";
		
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(String userid,  HttpSession session ) {
		
		int registCnt = userService.deleteUser(userid);
		
		
		if (registCnt == 1 ) {
			
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
		else return "user/modifyUser";
		
	}
	
	@RequestMapping("excelDownload")
	public String excelDownload( Model model ) {
		
		List<String> header = new ArrayList<String>();
		
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
	// localhot/user/profile
	@RequestMapping("profile")
	public void profile( HttpServletResponse resp, String userid, HttpServletRequest req ) {
		
		resp.setContentType("image");
		
		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
		
		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		if(userVo.getRealfilename()==null) {
			
			path = req.getServletContext().getRealPath("/image/unknown.png");
		} else {
			
			path = userVo.getRealfilename();
		}
		
		logger.debug("path : {}",path);
		
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				
				sos.write(buff);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	// localhot/user/profile
	@RequestMapping("profileDownload")
	public void profileDownload( HttpServletResponse resp, String userid, HttpServletRequest req ) {

		resp.setContentType("image");
		
		UserVo userVo = userService.selectUser(userid);
		String path = "";
		String filename="";
		
		if(userVo.getRealfilename()==null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
			filename = "unkonwon.png";
		} else {
			path = userVo.getRealfilename();
			filename = userVo.getFilename();
		}
		resp.setHeader("Content-Disposition","attachment; filename=" + filename);
		
		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
		
		
		logger.debug("path : {}",path);
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				
				sos.write(buff);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
