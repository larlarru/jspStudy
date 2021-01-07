package kr.or.ddit.cookie;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtilTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieUtilTest.class);

	
	// 테스트 메소드 명명 규칙 : 테스트할 메소드+"TEST" 
	@Test
	public void test() {
		/***Given***/
		String cookieStr = "userid=brown; rememberme=Y; test=testcookie";
		String cookieName = "rememberme";

		/***When***/
		String cookieValue = CookieUtil.getCookieValue(cookieStr, cookieName);
		logger.debug("cookieValue : " + cookieValue);
		/***Then***/
		assertEquals("Y", cookieValue);
	}
	
	@Test
	public void test2() {
		/***Given***/
		String cookieStr = "userid=brown; rememberme=Y; test=testcookie";
		String cookieName = "userid";
		logger.debug("cookieStr : "+cookieStr);
		logger.debug("cookieName : "+cookieName);
		
		/***When***/
		String cookieValue = CookieUtil.getCookieValue(cookieStr, cookieName);
		
		/***Then***/
		assertEquals("brown", cookieValue);
	}
	
	// 밑에처럼 할거면 공백을 return 한다는 규약이 있어야 한다.
	@Test
	public void testValue3Test() {
		/***Given***/
		String cookieStr = "userid=brown; rememberme=Y; test=testcookie";
		String cookieName = "userid";
		logger.debug("cookieStr : "+cookieStr);
		logger.debug("cookieName : "+cookieName);
		
		/***When***/
		String cookieValue = CookieUtil.getCookieValue(cookieStr, cookieName);
		
		/***Then***/
		assertEquals("", cookieValue);
	}

}
