package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAOOrclImpl implements PhoneBookDAO {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "HR", "1234");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	@Override
	public List<PhoneBook> getList() {
		List<PhoneBook> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT id, name, hp, tel, creat_at " +
								FROM emaillist ORDER BY create_at DESC";"
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(4);
				Date creatAt = rs.getDate(5);
				
				EmailVo vo = new EmailVo(no, lastName, firstName, email, creatAt);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	@Override
	public int insert(EmailVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO emaillist" +
							"(no, last_name, first_name, email) " +
							"VALUES(seq_emaillist_pk.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  vo.getLastName());
			pstmt.setString(2,  vo.getFirstName());
			pstmt.setString(3,  vo.getEmail());
			
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return insertedCount;
	}
	
	@Override
	public int delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM emaillist " +
							"WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount;
	}

	@Override
	public int addPhone(PhoneBook phoneBook) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePhone(int deleteId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PhoneBook> getSearch(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
