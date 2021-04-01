package com.bit.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAOImpl implements PhoneBookDAO {
	
	private Connection getConnection() throws SQLException {
		String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "hr";
		String password = "1234";
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dburl, userName, password);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return conn;
	}

	/*
	List<PhoneBook> getList();
	int insert(PhoneBook phoneBook);
	int delete(int deleteId);
	List<PhoneBook> getSearch(String keyword);
    */
	
	public List<PhoneBook> getList() {
		List<PhoneBook> phoneBookList = new ArrayList<PhoneBook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, name, hp, tel FROM PHONE_BOOK");
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
				
				PhoneBook phoneBook = new PhoneBook(id, name, hp, tel);
				phoneBookList.add(phoneBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return phoneBookList;
	}
	
	public int insert(PhoneBook phoneBook) {
		int insertCount = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			insertCount = stmt.executeUpdate("INSERT INTO PHONE_BOOK(id, name, hp, tel) VALUES(SEQ_PHONE_BOOK.NEXTVAL, '"
									+ phoneBook.getName() + "', '"
									+ phoneBook.getHp() + "', '"
									+ phoneBook.getTel() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return insertCount;
	}
	
	public int delete(Integer id) {
		int deleteCount = 0;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			deleteCount = stmt.executeUpdate("DELETE FROM PHONE_BOOK WHERE id = " + id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return deleteCount;
	}
	
	public List<PhoneBook> getSearch(String keyword) {
		List<PhoneBook> phoneBookList = new ArrayList<PhoneBook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, name, hp, tel FROM PHONE_BOOK WHERE name like '" + keyword + "%'");
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
				
				PhoneBook phoneBook = new PhoneBook(id, name, hp, tel);
				phoneBookList.add(phoneBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return phoneBookList;
	}

}








