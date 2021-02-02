package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
//@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
//	@Resource(name="userDao")
//	private UserDao userDao;
	
	public UserServiceImpl() {}
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

//	@Override
//	public List<UserVo> selectPagingUser(PageVo pageVo) {
//		return userDao.selectPagingUser(pageVo);
//	}
	
	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("pageVo", pageVo);
		resultMap.put("userList", userDao.selectPagingUser(pageVo));

//		resultMap.put("userCnt", userDao.selectAllUserCnt());
		
//		int userCnt = userDao.selectAllUserCnt();
		
		resultMap.put("pagination", 
				(int)Math.ceil( (double)userDao.selectAllUserCnt() )/pageVo.getPageSize() );
				
//		resultMap.put("pagination", 
//				(int)Math.ceil( ((Integer)resultMap.get("userCnt")).doubleValue()/pageVo.getPageSize()) );
		
		
		return resultMap;
	}
	

	@Override
	public int modifyUser(UserVo userVo) {
		return userDao.modifyUser(userVo);
	}

	@Override
	public int deleteUser(String string) {
		return userDao.deleteUser(string);
	}

	@Override
	public int registUser(UserVo userVo) {
		return userDao.registUser(userVo);
	}

	@Override
	public int selectAllUserCnt() {
		return userDao.selectAllUserCnt();
	}

	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	
}
