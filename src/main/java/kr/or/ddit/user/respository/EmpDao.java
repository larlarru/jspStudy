package kr.or.ddit.user.respository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.EmpVo;
import kr.or.ddit.db.MybatisUtil;

public class EmpDao implements EmpDaoI{

	private static final Logger logger = LoggerFactory.getLogger(EmpDao.class);
	
	@Override
	public List<EmpVo> selectAllEmp() {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<EmpVo> empList = sqlSession.selectList("emp.selectAllEmp");
		
		sqlSession.close();
		
		return empList;
		
	}



}
