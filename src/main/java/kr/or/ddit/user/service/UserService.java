package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	UserVo getUser(String userid);

	List<UserVo> selectAllUser();

	List<UserVo> selectPagingUser(PageVo pageVo);

	int modifyUser(UserVo userVo);

	int deleteUser(String string);

	int registUser(UserVo userVo);
	
}
