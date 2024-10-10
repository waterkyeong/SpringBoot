package edu.pnu.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;

public class MemberService {

	private MemberDAO memberDAO;
	
	public MemberService() {
		try {
			memberDAO = new MemberDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//검색(Read - select)

	public List<MemberDTO> getAllMember() throws SQLException{
		return memberDAO.getAllMember();
	}

	public MemberDTO getMemberById(Integer id) throws SQLException {
		return memberDAO.getMemberById(id);
	}

	//입력(Create - insert)
	public MemberDTO addMember(MemberDTO memberDTO) throws SQLException {
		return memberDAO.addMember(memberDTO);
	}

	//수정(Update - update)
	public int updateMember(MemberDTO memberDTO) throws SQLException {
		return memberDAO.updateMember(memberDTO);
	}

	//삭제(Delete - delete)
	public int deleteMember(Integer id) throws SQLException {
		return memberDAO.deleteMember(id);
	}

}
