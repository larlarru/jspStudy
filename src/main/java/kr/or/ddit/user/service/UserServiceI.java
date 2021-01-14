package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserServiceI {
	
	List<UserVo> selectAllUser();
	
	UserVo selectUser(String userid);
	
	Map<String, Object> selectPagingUser(PageVo pageVo);
//	List<UserVo> selectPagingUser(PageVo pageVo);
	
	// 사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	int insertUser(UserVo userVo);
	
}
