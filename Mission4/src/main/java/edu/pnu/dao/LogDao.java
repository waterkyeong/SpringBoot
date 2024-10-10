package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.LogDTO;

public class LogDao {

	private Connection con;

	public LogDao() throws SQLException{
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootmission","scott","tiger");
	}

	public void addLog(String sqlstring, String method, Integer success) {
		String sql = "INSERT INTO dblog (sqlstring, method, success) VALUES (?,?,?)";
		
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, sqlstring);
			psmt.setString(2, method);
			psmt.setInt(3, success);
			psmt.executeUpdate();
			
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//	public List<LogDTO> getAllLogs() {
//		List<LogDTO> list = new ArrayList<>();
//
//		String query = "select * from dblog order by id";
//		
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(query);
//			while(rs.next()) {
//				list.add(LogDTO.builder()
//					.trace(rs.getString("trace"))
//					.info(rs.getString("info"))
//					.debug(rs.getString("debug"))
//					.error(rs.getDate("error"))
//					.warn(rs.getString("warn"))
//						.build());
//				stmt.close();
//				rs.close();
//			} 
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return list;
//	}
}
