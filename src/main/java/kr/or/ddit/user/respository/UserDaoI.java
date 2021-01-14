package kr.or.ddit.user.respository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDaoI {
	
	// 전체 사용자 정보 조회
	/*
	 * SELECT *
	 * FROM users
	 * WHERE userid = ?
	 */
	
	// 반환타입 메소드명();
	List<UserVo> selectAllUser();
	
	UserVo selectUser(String userid);
	
	List<UserVo> selectPagingUser(PageVo pageVo);
	
	// 사용자 전체 수 조회
	int selectAllUserCnt();
	
	// 사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	int insertUser(UserVo userVo);
	
}
