package kr.or.ddit.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceI;
import kr.or.ddit.util.FileUtil;

@MultipartConfig
@WebServlet("/userModify")
public class UserModifyUser extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(UserModifyUser.class);
	private UserServiceI userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		
		UserVo user = userService.selectUser(userid);
		
		req.setAttribute("user", user);
		
		req.getRequestDispatcher("/user/userModify.jsp").forward(req, resp);
		
	}
	
	// 사용자 정보 수정 요청 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터를 읽기 전에 실행
		// servlet doPost 메소드 마다 실행 필요 ==> Filter
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("usernm");
		String pass = req.getParameter("pass");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		Date reg_dt = null;
		reg_dt = new Date();
		
		logger.debug("req.getParameter의 reg_dt : " + req.getParameter("reg_dt"));
//		
//		try {
//			reg_dt = sdf.parse(req.getParameter("reg_dt"));
//			logger.debug("reg_dt : " + reg_dt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		
		String alias = req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		// 사용자가 profile을 업로드한 경우
		// 전송한 파일 이름(filename)
		// 서버에 저장할 파일이름(realfilename)
		// 서버에 지정된 공간에 저장
		
		String filename="";
		String realfilename = "";
		
		Part profile = req.getPart("profile");
		if(profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
//			String filename2 = new String(filename.getBytes("8859_1"), "UTF-8");
			String fileExtension = FileUtil.getFileExtension(filename);
			
			logger.debug("등록의 write전 filename : "+filename);
//			logger.debug("등록의 write전 filename2 : "+filename2);
			logger.debug("등록의 write전 fileExtension : "+fileExtension);
			
			// brown / brown.png
			realfilename = UUID.randomUUID().toString()+fileExtension;
//			String realfilename2 = new String(realfilename.getBytes("8859_1"), "UTF-8");
			
//			logger.debug("등록의 write전 realfilename2 : "+realfilename2);
			
			profile.write("d:\\upload\\"+realfilename);
//			profile.write("d:\\A_TeachingMaterial\\6.JspSpring\\workspace\\jsp\\src\\main\\webapp"+realfilename);
		}
		
		else {
			UserVo uservo1 = userService.selectUser(userid);
			filename = uservo1.getFilename();
			realfilename = uservo1.getRealfilename();
		}
		
		UserVo uservo = new UserVo(userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode, filename, realfilename);
		
		int updateCnt = userService.modifyUser(uservo);
		logger.debug("updateCnt : " + updateCnt);
		// 사용자 수정이 정상적으로 된 경우 ==> 해당 사용자의 상세조회 페이지로 이동
		if(updateCnt == 1) {
			resp.sendRedirect(req.getContextPath()+"/user?userid="+userid);
		}
		// 사용자 수정이 비정상적으로 된 경우 ==> 해당 사용자의 정보 수정 페이지로 이동
		else {
			doGet(req,resp);
		}
		
	}
	
}
