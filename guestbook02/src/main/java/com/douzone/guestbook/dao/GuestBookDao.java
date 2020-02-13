package com.douzone.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.guestbook.vo.GuestBookVo;

public class GuestBookDao {
	
	public List<GuestBookVo> findAll() {
		List<GuestBookVo> result = new ArrayList<GuestBookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = 
					"select no, name, contents, ng_date" + 
					" from guestbook";
			
			pstmt = conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
	
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String ngDate = rs.getString(4);
			
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setNgDate(ngDate);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {	
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}	
		
		return result;
		
	}
	
	public Boolean insert(GuestBookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = 
					"insert" + 
					" into guestbook" + 
					" values(null, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,vo.getName());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getPassword());
			pstmt.setNString(4, vo.getNgDate());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {	
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}
		
		return result;
		
	}
	
	public boolean delete(GuestBookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete" +
							" from guestbook" +
							" where no = ? and password = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			
			int count = pstmt.executeUpdate();
			
			if(count == 1) result = true;
			else result = false;
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {	
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}
		
		return result;
	
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			// 1. jdbc driver(myDriver) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		}  catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		return conn;
	}

}
