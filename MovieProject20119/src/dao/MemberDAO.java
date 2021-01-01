package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.JDBCUtil;
import vo.MemberVO;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {}

	public MemberVO getLoginData(String outId, String outPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO vo = null;
		
		try {
			String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outId);
			pstmt.setString(2, outPw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String email = rs.getString(3);
				String tel = rs.getString(4);
				Timestamp birth = rs.getTimestamp(5);
				
				vo = new MemberVO(id, pw, email, tel, birth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return vo;
	}
	
	public int register(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?)";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTel());
			pstmt.setTimestamp(5, vo.getBirth());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pstmt, conn);
		}
		
		return result;
	}
	
	public boolean getDuplicateUserData(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return result;
	}
	
	public String idFind(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		
		try {
			String sql = "SELECT id FROM member WHERE email = ? AND tel = ? AND birth = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getTel());
			pstmt.setTimestamp(3, vo.getBirth());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return id;
	}
	
	public boolean getId(String outId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean exits = false;
		
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				exits = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return exits;
	}
	
	public int pwChange(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "UPDATE member SET pw = ? WHERE id = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pstmt, conn);
		}
		
		return result;
	}
	
	public Timestamp changeDate(String year, String month, String day) {
		if(Integer.parseInt(month) < 9) {
			month = "0" + month;
		}
		if(Integer.parseInt(day) < 9) {
			day = "0" + day;
		}
		
		String date = year + "-" + month + "-" + day;
		Date now = Date.valueOf(date);
		Timestamp birth = new Timestamp(now.getTime());
		
		return birth;
	}
	
	public String changeTel(String outTel) {
		String tel = null;
		
		if(outTel.length() == 10) {
			String first = outTel.substring(0, 3);
			String second = outTel.substring(3, 6);
			String third = outTel.substring(6, outTel.length());
			
			tel = first + "-" + second + "-" + third;
		} else if(outTel.length() == 11) {
			String first = outTel.substring(0, 3);
			String second = outTel.substring(3, 7);
			String third = outTel.substring(7, outTel.length());
			
			tel = first + "-" + second + "-" + third;
		}
		
		return tel;
	}
}