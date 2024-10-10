package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberDTO;

public class MemberDAO {

	private Connection con;

	public MemberDAO() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootmission","scott","tiger");
	}

	//검색(Read - select)
	public List<MemberDTO> getAllMember() throws SQLException {
		List<MemberDTO> list = new ArrayList<>();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from member order by id");
		while(rs.next()) {
			list.add(MemberDTO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build());
		}
		stmt.close();
		rs.close();
		
		return list;
	}

	public MemberDTO getMemberById(Integer id) throws SQLException {
		MemberDTO dto = new MemberDTO();
		String sql = "select * from member where id=?";
		
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()) {
			dto.setId(rs.getInt("id"));
			dto.setName(rs.getString(2));
			dto.setPass(rs.getString(3));
			dto.setRegidate(rs.getDate(4));
		}
		
		psmt.close();
		rs.close();
		
		return dto;
	}

	//입력(Create - insert)
	public MemberDTO addMember(MemberDTO memberDTO) throws SQLException {
		
		String sql = "INSERT INTO member (id,name, pass) VALUES (?,?,?)";
		
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setInt(1, memberDTO.getId());
		psmt.setString(2, memberDTO.getName());
		psmt.setString(3, memberDTO.getPass());
		psmt.executeUpdate();
		
		psmt.close();
		
		return getMemberById(memberDTO.getId());
	}

	//수정(Update - update)
	public int updateMember(MemberDTO memberDTO) throws SQLException {
		
		String sql = "UPDATE member SET name = ?, pass = ? WHERE id = ?";
		
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1, memberDTO.getName());
		psmt.setString(2, memberDTO.getPass());
		psmt.setInt(3, memberDTO.getId());
		
		int result = psmt.executeUpdate();
		
		psmt.close();
		
		return result;
	}

	//삭제(Delete - delete)
	public int deleteMember(Integer id) throws SQLException {
		
		String sql = "DELETE FROM member WHERE id =?";
		
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setInt(1, id);
		
		int result = psmt.executeUpdate();
		
		psmt.close();
		
		return result;
	}
	
	
}
