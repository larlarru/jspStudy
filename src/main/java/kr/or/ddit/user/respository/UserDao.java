package kr.or.ddit.user.respository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.user.model.UserVo;

public class UserDao implements UserDaoI{

	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Override
	public List<UserVo> selectAllUser() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		// select : 리턴되는 값의 복수 유무를 판단
		//			1. 단건 : 일반객체를 반환(UserVo) selectOne()
		//			2. 여러건 : List<UserVo> selectList()
		// insert, update, delete : insert, update, delete
		
		// 메소드 이름과 실행할 sql id를 동일하게 맞추자(유지보수-다른 개발자의 가독성)
		
		List<UserVo> userList = sqlSession.selectList("users.selectAllUser");
		
		// 사용한 자원 반환
		sqlSession.close();
		
		return userList;
	}

	// userid에 해당하는 사용자 한명의 정보 조회
	public UserVo selectUser(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		UserVo user = sqlSession.selectOne("users.selectUser", userid);
		if(user!=null) logger.debug("user값 null아님");
		else logger.debug("UserDao에 selectUser 부분 오류");
		sqlSession.close();
		
		return user;
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo pageVo) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<UserVo> userList = sqlSession.selectList("users.selectPagingUser", pageVo);
		
		sqlSession.close();
		
		return userList;
	}

	@Override
	public int selectAllUserCnt() {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int userCnt = sqlSession.selectOne("users.selectAllUserCnt");
		sqlSession.close();
		
		return userCnt;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("users.modifyUser",userVo);
		
		if (updateCnt==1) sqlSession.commit();
		else sqlSession.rollback();
		
		sqlSession.close();
		
		return updateCnt;
	}

	@Override
	public int insertUser(UserVo userVo) {
		int insertCnt = 0;
		try {
			
			SqlSession sqlSession = MybatisUtil.getSqlSession();
			insertCnt = sqlSession.insert("users.insertUser",userVo);
			logger.debug("insertCnt : "+insertCnt);
			
			if (insertCnt==1) sqlSession.commit();
			else sqlSession.rollback();
			
			sqlSession.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return insertCnt;
	}


}
