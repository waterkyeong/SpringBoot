package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.LogDTO;
//import edu.pnu.dao.LogDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {

	private MemberDAO memberdao;
	private LogDao logdao;
	
	public MemberService() {
		try {
			memberdao = new MemberDAO(); 
			logdao = new LogDao(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//검색(Read - select)
	public List<MemberDTO> getAllMember() throws SQLException{
		Map<String, Object> map = memberdao.getAllMember();
		
		@SuppressWarnings("unchecked")
		List<MemberDTO> list = (List<MemberDTO>)map.get("result");

		String sql = (String)map.get("sqlstring");
		Integer success = (Integer)map.get("success");
		
		logdao.addLog(sql,  "GET", success);
		
		
		return list;
	}

	public MemberDTO getMemberById(Integer id) throws SQLException {
		
		Map<String, Object> map = (Map<String, Object>) memberdao.getMemberById(id);
		
		String sql = (String)map.get("sqlstring");
		Integer sucess = (Integer)map.get("success");
		
		logdao.addLog(sql, "GET", sucess);
		
		return (MemberDTO)map.get("result");
	}

	//입력(Create - insert)
	public MemberDTO addMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = memberdao.addMember(memberDTO);
		
		String sql = (String)map.get("sqlstring");
		Integer success = (Integer)map.get("success");
		
		logdao.addLog(sql, "POST", success);
		
		return (MemberDTO)map.get("result");
	}
	
	//수정(Update - update)
	public int updateMember(MemberDTO memberDTO) throws SQLException {
		Map<String, Object> map = memberdao.updateMember(memberDTO);
		
		String sql = (String)map.get("sqlstring");
		Integer success = (Integer)map.get("success");
		
		logdao.addLog(sql, "POST", success);
		
		return (int)map.get("result");
	}

	//삭제(Delete - delete)
	public int deleteMember(Integer id) throws SQLException {
		Map<String,Object> map = memberdao.deleteMember(id);
		
		String sql = (String)map.get("sqlstring");
		Integer success = (Integer)map.get("success");
		
		logdao.addLog(sql, "DELETE", success);
		
		return (int)map.get("result");
	}

//	public List<LogDTO> getAllLogs() {
//		return logdao.getAllLogs();
//	}
}
