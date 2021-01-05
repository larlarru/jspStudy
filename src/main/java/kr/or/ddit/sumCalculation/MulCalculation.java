package kr.or.ddit.sumCalculation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/mulCalculation")
public class MulCalculation extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(MulCalculation.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * resp.setContentType("text/html;charset=utf-8");
		 * 
		 * PrintWriter pw = resp.getWriter();
		 * 
		 * pw.println("<html>"); pw.println("	<head>");
		 * pw.println("		<title>mulCalculation<title>"); pw.println("	</head>");
		 * pw.println("	<body>");
		 * pw.println("		<form action='<%=request.getContextPath()%>/mulCalculation' method='post'>"
		 * ); pw.println("		<form action='' method='post'>");
		 * pw.println("			<input type='text' name='satrt'>");
		 * pw.println("			<input type='text' name='end'>");
		 * pw.println("			<input type='submit' value='전송'>");
		 * pw.println("		</form>"); pw.println("	</body>"); pw.println("</html>");
		 * 
		 * pw.flush(); pw.close();
		 */
		
		req.getRequestDispatcher(req.getContextPath()+"/jsp/mulCalculation.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int start = Integer.parseInt(req.getParameter("start"));
		int end = Integer.parseInt(req.getParameter("end"));
	
		if(start > end) {
			req.getRequestDispatcher(req.getContextPath()+"/jsp/mulCalculation.jsp").forward(req, resp);
		}
		
		HttpSession session = req.getSession();
		
		
		session.setAttribute("mulResult", start*end);
		req.getRequestDispatcher(req.getContextPath()+"/jsp/mulCalculation.jsp").forward(req, resp);
		
		
		
	}
	
}
