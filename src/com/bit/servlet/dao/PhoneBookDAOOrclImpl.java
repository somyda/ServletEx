package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
								"FROM emaillist ORDER BY create_at DESC" ;
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				Integer hp = rs.getInt(3);
				Integer tel = rs.getInt(4);
				
				PhoneBook vo = new PhoneBook();
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
	

	public int insert(PhoneBook vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO phonebooklist" +
							"(id, name, hp, tel) " +
							"VALUES(seq_phonebooklist_pk.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  vo.getName());
			
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
