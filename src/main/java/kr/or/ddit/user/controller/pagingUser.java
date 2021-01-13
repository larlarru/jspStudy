package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceI;

@WebServlet("/pagingUser")
public class pagingUser extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(pagingUser.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// left.jsp : /pagingUser?page=1&pageSize=5
		// ==> /pagingUser
		// doGet 메소드에서 page, pageSize 파라미터가 request 객체에 존재 하지 않을 때
		// page는 1로, pageSize 5로 생각을 코드로 작성
		// 파라미터가 존재하면 해당 파라미터를 이용
		
		// primitive Type ==> class(Wrapper) Integer
		// if /else ==> //조건 ? true 일때 반환할 값 : false일때 반환할 값
		// refactoring : 코드를 (깔끔하게)바꾸는데 기존 동작방식을 유지한채로 변경하는 기법
		
		/*
		 * - request 객체에서 제공하는 파라미터 관련 메소드
		 * 
		 */
		
		UserServiceI userService = new UserService();
		
		String pageParam = req.getParameter("page");
		logger.debug("pageParam : "+pageParam);
		String pageSizeParam = req.getParameter("pageSize");
		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = pageSizeParam == null ? 5 : Integer.parseInt(pageSizeParam);
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		int userCnt = (int)map.get("userCnt");
		int pagination = (int)Math.ceil((double) userCnt / pageSize); 
		
		req.setAttribute("pageVo", pageVo);
		req.setAttribute("userList", userList);
		req.setAttribute("pagination", pagination);
		
		//req.getRequestDispatcher("/user/allUser.jsp").forward(req, resp);
		
		req.getRequestDispatcher("/user/pagingUser.jsp").forward(req, resp);
		
		/*
		 * int page = req.getParameter("page") == null ? 1 :
		 * Integer.parseInt(req.getParameter("page")); int pageSize =
		 * req.getParameter("pageSize") == null ? 5 :
		 * Integer.parseInt(req.getParameter("pageSize"));
		 * 
		 * UserServiceI userService = new UserService();
		 * 
		 * PageVo pagevo = new PageVo();
		 * 
		 * 
		 * logger.debug("int page : " + page); logger.debug("int pageSize : " +
		 * pageSize); logger.debug("page : " + req.getParameter("page"));
		 * logger.debug("pageSize : " + req.getParameter("pageSize"));
		 * pagevo.setPage(page); pagevo.setPageSize(pageSize);
		 * 
		 * Map<String, Object> map = userService.selectPagingUser(pagevo);
		 * 
		 * List<UserVo> userPagingList = (List<UserVo>) map.get("userList");
		 * 
		 * 
		 * 
		 * if(userPagingList==null) logger.debug("userPagingList null"); else
		 * logger.debug("userPagingList not null");
		 * 
		 * req.setAttribute("userPagingList", userPagingList);
		 * req.setAttribute("pagination", 4);
		 * 
		 * req.getRequestDispatcher("/user/pagingUser.jsp").forward(req, resp);
		 */
		
	}
	
}
