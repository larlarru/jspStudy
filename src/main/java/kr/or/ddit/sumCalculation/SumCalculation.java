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

@WebServlet("/sumCalculation")
public class SumCalculation extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(SumCalculation.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		
		pw.println("<html>");
		pw.println("	<head>");
		pw.println("		<title>SumCalculation<title>");
		pw.println("	</head>");
		pw.println("	<body>");
		pw.println("		<form action='<%=request.getContextPath()%>/sumCalculation' method='post'>");
		pw.println("			<input type='text' name='satrt'>");
		pw.println("			<input type='text' name='end'>");
		pw.println("			<input type='submit' value='전송'>");
		pw.println("		</form>");
		pw.println("	</body>");
		pw.println("</html>");
		
		pw.flush();
		pw.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int start = Integer.parseInt(req.getParameter("start"));
		int end = Integer.parseInt(req.getParameter("end"));
		
		if(start > end) {
			req.getRequestDispatcher(req.getContextPath()+"/jsp/sumCalculation.jsp").forward(req, resp);
		}
		
		HttpSession session = req.getSession();
		int sum=0;
		for(int i = start; i <= end; i++) {
			sum += i;
		}
		
		session.setAttribute("sumResult", sum);
		req.getRequestDispatcher(req.getContextPath()+"/jsp/sumCalculation.jsp").forward(req, resp);
		
		
		
	}
	
	
}
