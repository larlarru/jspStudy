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
@WebServlet("/registUser")
public class RegistUser extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(RegistUser.class);
	private UserServiceI userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(req.getContextPath()+"/user/registUser.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터를 읽기 전에 실행
				// servlet doPost 메소드 마다 실행 필요 ==> Filter
				req.setCharacterEncoding("utf-8");
				
				String userid = req.getParameter("userid");
				String usernm = req.getParameter("usernm");
				String pass = req.getParameter("pass");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//				Date reg_dt = null;
				Date reg_dt = new Date();
				/*
				 * try { reg_dt = sdf.parse(req.getParameter("reg_dt"));
				 * logger.debug("reg_dt : "+reg_dt); } catch (Exception e) {
				 * e.printStackTrace(); }
				 */
				
				String alias = req.getParameter("alias");
				String addr1 = req.getParameter("addr1");
				String addr2 = req.getParameter("addr2");
				String zipcode = req.getParameter("zipcode");
				
				Part profile = req.getPart("profile");
				
				String filename="";
				String realfilename = "";
				
				if(profile.getSize() > 0) {
					filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
//					String filename2 = new String(filename.getBytes("8859_1"), "UTF-8");
					logger.debug("인코딩한 filename : "+filename);
//					String fileExtension = FileUtil.getFileExtension(filename);
					String fileExtension = FileUtil.getFileExtension(filename);
//					filename = filename2;
					// brown / brown.png
					realfilename = UUID.randomUUID().toString()+fileExtension;
					
					
					
					profile.write("d:\\upload\\"+realfilename);
					profile.write("d:\\A_TeachingMaterial\\6.JspSpring\\workspace\\jsp\\src\\main\\webapp"+realfilename);
				}
				
				logger.debug("Insert에 uservo에 값넣기전 filename : " + filename);
				logger.debug("Insert에 uservo에 값넣기전 realfilename : " + realfilename);
				
				UserVo uservo = new UserVo(userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode, filename, realfilename);
				
				int insertCnt = userService.insertUser(uservo);
				
				// 사용자 수정이 정상적으로 된 경우 ==> 해당 사용자의 상세조회 페이지로 이동
				if(insertCnt == 1) {
					resp.sendRedirect(req.getContextPath()+"/pagingUser");
				}
				// 사용자 수정이 비정상적으로 된 경우 ==> 해당 사용자의 정보 수정 페이지로 이동
				else {
					resp.sendRedirect("/user/registUser.jsp");
				}
		
	}
	
}
