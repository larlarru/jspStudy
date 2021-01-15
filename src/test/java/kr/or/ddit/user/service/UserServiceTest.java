package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest {
	private UserServiceI userService;
	
	@Before
	public void setup() {
		userService = new UserService();
		
		//테스트 에서 사용할 신규 사용자 추가
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass",
				new Date(), "대덕", "대전 중구 중앙로 76", "4층", "34940");
		
		userService.registUser(userVo);
		
		//신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
		userService.deleteUser("ddit_n");
		
		//@Before 	==> 	@Test registUser ==>	@After
		//18+1(testUser)	-1(ddit_n) +1(ddit_n) 	-1(testUser)
	}
	/*
	@Before
		1. testUser 삭제
		1. testUser 등록
	@Test
		테스트 실행
	*/
	@After
	public void tearDodwn() {
		userService.deleteUser("testUser");
	}
	
	
	
	//전체 사용자 조회 테스트
	@Test
	public void selectAllUserTest() {
		/***Given***/
//		UserServiceI UserService = new UserService();
		
		/***When***/
		List<UserVo> userList = userService.selectAllUser();

		/***Then***/
		assertEquals(17, userList.size());
	}
	
	//사용자 아이디를 이용하여 특정 사용자 정보 조회
	@Test
	public void selectUserTest() {
		/***Given***/
//		UserServiceI UserService = new UserService();
		String userid = "brown";

		/***When***/
		UserVo user	= userService.selectUser(userid);

		/***Then***/
		assertNotNull(user);
		assertEquals("브라운", user.getUsernm());
	}
	
	//사용자 페이징 조회 테스트
	@Test
	public void selectPagingUserTest() {
		/***Given***/
//		UserServiceI UserService = new UserService();
		PageVo pageVo = new PageVo(2, 5);
		
		/***When***/
		//List<UserVo> userList = UserService.selectPagingUser(page, pagesize);
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		

		/***Then***/
		assertEquals(5, userList.size());
	}
	
	@Test
	public void selectAllUserCntTest() {
		/***Given***/
//		UserServiceI UserService = new UserService();
		
		/***When***/
		int userCnt = userService.selectAllUserCnt();

		/***Then***/
		assertEquals(17, userCnt);
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
//		UserServiceI UserService = new UserService();
		
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditpass", new Date(),
								   "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940");
		
		/***When***/
		int updateCnt = userService.modifyUser(userVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		/***Given***/
//		UserServiceI UserService = new UserService();

		
		//userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit_n", "대덕인재", "dditpass", new Date(),
								   "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940");
		
		/***When***/
		int insertCnt = userService.registUser(userVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
/*	모든 테스트 코드 실행전에
	테스트에 참여할 수 있는 임의의 사용자를 입력 */

	//삭제 테스트
	@Test
	public void deleteUserTest() {
		/***Given***/
		//해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된 상태
		String userid = "testUser";

		/***When***/
		int deleteCnt = userService.deleteUser(userid);
		/***Then***/
		assertEquals(1, deleteCnt);
	}
}









