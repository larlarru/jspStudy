package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	// 사용자 아이디로 자용사 조회
	UserVo getUser(String suerid);
	
	List<UserVo> selectAllUser();
	
	List<UserVo> selectPagingUser(PageVo pageVo);
	
	int modifyUser(UserVo userVo);

	int registUser(UserVo userVo);

	int deleteUser(String userid);
	
	int selectAllUserCnt();
	
	UserVo selectUser(String userid);
	
}
