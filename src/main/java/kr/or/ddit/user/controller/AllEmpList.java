package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.EmpVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.EmpService;
import kr.or.ddit.user.service.EmpServiceI;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceI;

@WebServlet("/allEmpList")
public class AllEmpList extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(AllEmpList.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EmpServiceI empService = new EmpService();
		
		List<EmpVo> empList = empService.selectAllEmp();
		
		if(empList!=null) logger.debug("empList not null");
		else logger.debug("empList null");
		
		req.setAttribute("empList", empList);
		
		req.getRequestDispatcher("/user/allEmp.jsp").forward(req, resp);
		
		
	}
	
}
