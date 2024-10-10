package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberDTO;

public class MemberDAO {

	private Connection con;

	public MemberDAO() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootmission", "scott", "tiger");
	}

	//검색(Read - select)
	public Map<String, Object> getAllMember() throws SQLException {

		Map<String, Object> map = new HashMap<>();
		List<MemberDTO> list = new ArrayList<>();

		String query = "select * from member order by id";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
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

		map.put("result",  list);
		map.put("sqlstring", query);
		map.put("success", 1);

		return map;
	}

	public Map<String, Object> getMemberById(Integer id) {

		Map<String, Object> map = new HashMap<>();
		MemberDTO dto = new MemberDTO();

		String sql = "select * from member where id=?";

		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
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

			map.put("result", dto);
			map.put("sqlstring", sql);
			map.put("success", 1);

		} catch (SQLException e) {
			map.put("result", dto);
			map.put("sqlstring", sql);
			map.put("success", 0);
			e.printStackTrace();
			System.out.println("id가 존재하지않습니다.");
		}


		return map;
	}

	//입력(Create - insert)
	public Map<String, Object> addMember(MemberDTO memberDTO) {

		Map<String, Object> map = new HashMap<>();

		String sql = "INSERT INTO member (id,name, pass) VALUES (?,?,?)";

		PreparedStatement psmt = null;
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, memberDTO.getId());
			psmt.setString(2, memberDTO.getName());
			psmt.setString(3, memberDTO.getPass());
			result = psmt.executeUpdate();

			psmt.close();

			map.put("result", result);
			map.put("sqlstring", sql);
			if(result == 1) {
				map.put("success", 1);
			}else {
				map.put("success",0);
			}
		} catch (SQLException e) {
			map.put("result", result);
			map.put("sqlstring", sql);
			map.put("success", 0);
			e.printStackTrace();
			System.out.println("member 추가 과정 중 오류가 났습니다.");
		}
		return getMemberById(memberDTO.getId());
	}

	//수정(Update - update)
	public Map<String, Object> updateMember(MemberDTO memberDTO) {

		Map<String, Object> map = new HashMap<>();

		String sql = "UPDATE member SET name = ?, pass = ? WHERE id = ?";

		PreparedStatement psmt = null;
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, memberDTO.getName());
			psmt.setString(2, memberDTO.getPass());
			psmt.setInt(3, memberDTO.getId());

			result = psmt.executeUpdate();

			psmt.close();

			map.put("result",result);
			map.put("sqlstring",sql);
			// id가 없는 경우
			if(result == 1) {
				map.put("success", 1);
			}else {
				map.put("success", 0);
			}

		}catch(Exception e) {
			map.put("result",result);
			map.put("sqlstring",sql);
			map.put("success", 0);
			e.printStackTrace();
			System.out.println("member 내용 수정 중 오류가 났습니다.");
		}

		return map;
	}

	//삭제(Delete - delete)
	public Map<String, Object> deleteMember(Integer id) {

		Map<String, Object> map = new HashMap<>();

		String sql = "DELETE FROM member WHERE id =?";

		PreparedStatement psmt = null;
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);

			result = psmt.executeUpdate();

			psmt.close();

			map.put("result",result);
			map.put("sqlstring", sql);
			// id가 없는 경우
			if(result == 1) {
				map.put("success", 1);
			}else {
				map.put("success", 0);
			}
		}catch(Exception e) {
			map.put("result", result);
			map.put("sqlstring", sql);
			map.put("success", 0);
			e.printStackTrace();
			System.out.println("member 삭제 중 오류가 났습니다.");
		}

		return map;
	}


}
