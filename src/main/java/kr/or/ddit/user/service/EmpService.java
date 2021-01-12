package kr.or.ddit.user.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.EmpVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.user.respository.EmpDao;
import kr.or.ddit.user.respository.EmpDaoI;

public class EmpService implements EmpServiceI{

	private static final Logger logger = LoggerFactory.getLogger(EmpService.class);
	
	private EmpDaoI empDao = new EmpDao();
	
	@Override
	public List<EmpVo> selectAllEmp() {
		return empDao.selectAllEmp();
		
	}



}
