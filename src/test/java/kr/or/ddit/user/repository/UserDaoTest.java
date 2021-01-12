package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.respository.UserDao;
import kr.or.ddit.user.respository.UserDaoI;

public class UserDaoTest {
	
	// 전체 사용자 조회 테스트
	@Test
	public void selectAllUserTest() {
		/*** Given ***/

		UserDaoI userDao = new UserDao();

		/*** When ***/

		List<UserVo> userList = userDao.selectAllUser();

		/*** Then ***/
		assertEquals(16, userList.size());
	}

	// 사용자 아이디를 이용하여 특정 사용자 정보 조회
	@Test
	public void selectUserTest() {
		// 접근제어자
		/*** Given ***/
		UserDao userDao = new UserDao();
		String userid = "brown";

		/*** When ***/
		UserVo user = userDao.selectUser(userid);

		/*** Then ***/
		assertNotNull(user);
		assertEquals("brown", user.getUserid());
	}

	// 사용자 페이징 조회 테스트
	@Test
	public void selectPagingUser() {

		/*** Given ***/
		UserDaoI userDao = new UserDao();
		PageVo pagevo = new PageVo(2,5);

		/*** When ***/
		List<UserVo> userList = userDao.selectPagingUser(pagevo);

		/*** Then ***/
		assertEquals(pagevo.getPageSize(), userList.size());

	}

}
