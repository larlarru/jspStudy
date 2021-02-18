package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

// 스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록

public class UserDaoTest extends ModelTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Before
	public void setup() {
		
		//initData.sql을 실행 : 스프링에서 제공하는 ResourceDatabasePopulator
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		
		//populator를 통해 실행시킬 sql 파일을 지정
		populator.addScript(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		
		// script 파일을 실행하다 에러가 발생할 경우 더이상
		// 진행하지 않고 멈추게끔 설정
		populator.setContinueOnError(false);
		
		//populator를 실행
		DatabasePopulatorUtils.execute(populator, dataSource);

	}

	@Test
	public void test() {
		
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userid);

		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
		
	}
	
	@Test
	public void selectAllUserTest() {
		
		/***Given***/
		

		/***When***/
		List<UserVo> userVo = userDao.selectAllUser();
		logger.debug("List<UserVo> userVo.size {}",userVo.size());
		
		/***Then***/
		assertNotNull(userVo);
		
	}
	
	@Test
	public void selectPagingUser() {
		
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(10);
		
		/***When***/
		List<UserVo> userVo = userDao.selectPagingUser(pageVo);
		logger.debug("List<UserVo> selectPagingUser userVo.size {}",userVo.size());
		
		/***Then***/
		assertNotNull(userVo);
		
	}
	
	@Test
	public void modifyUser() {
		
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserid("cony");
		userVo.setUsernm("코니");
		userVo.setPass("conyPass");
		userVo.setAlias("토끼");
		userVo.setAddr1("실험수정1");
		userVo.setAddr2("실험수정2");
		userVo.setZipcode("");
		userVo.setFilename("");
		userVo.setRealfilename("");
		
		/***When***/
		int modifyCnt = userDao.modifyUser(userVo);
		logger.debug("modifyCnt :"+modifyCnt);
		
		/***Then***/
		assertEquals(1, modifyCnt);
		
	}
	
	@Test
	public void registUser() {
		
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserid("ddit_test2");
		userVo.setUsernm("ddit_test2");
		userVo.setPass("1234");
		userVo.setAlias("ddit_test2");
		userVo.setAddr1("ddit_test1");
		userVo.setAddr2("ddit_test2");
		userVo.setZipcode("");
		userVo.setFilename("");
		userVo.setRealfilename("");
		
		/***When***/
		int registCnt = userDao.registUser(userVo);
		logger.debug("registUser :"+registCnt);
		logger.debug("registUserId :"+userVo.getUserid());
		
		/***Then***/
		assertEquals(1, registCnt);
		
	}
	
	@Test
	public void deleteUser() {
		
		/***Given***/

		/***When***/
		int deleteCnt = userDao.deleteUser("ddit_test2");
		logger.debug("deleteCnt : " + deleteCnt);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void selectUserTest() {
		
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);

		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
		
	}
	
}
