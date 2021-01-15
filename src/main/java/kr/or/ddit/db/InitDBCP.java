package kr.or.ddit.db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(value="/initDBCP", loadOnStartup = 1)
public class InitDBCP extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(InitDBCP.class);
	
	@Override
	public void init() throws SecurityException {
		logger.debug("InitDBCP.init()");
		
		// 컨넥션 풀 생성
		// application scope에 컨넥션 풀 저장
		
		// dbcp.jsp에서는 application scope 저장된 컨넥션풀을 사용하여 컨넥션 객체를
		// 얻고 해제 하는 과정을 반복 - 시간 체크
		
	}
	
}
