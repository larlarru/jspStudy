package kr.or.ddit.user.respository;

import java.util.List;

import kr.or.ddit.common.model.EmpVo;

public interface EmpDaoI {
	
	List<EmpVo> selectAllEmp();
	
}
