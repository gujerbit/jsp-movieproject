package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.JDBCUtil;
import vo.MovieVO;
import vo.ScheduleVO;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();
	
	public static MovieDAO getInstance() {
		return instance;
	}
	
	public MovieDAO() {}
	
	public ArrayList<MovieVO> getMovieList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		
		try {
			String sql = "SELECT * FROM movie ORDER BY movieNo";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int movieNo = rs.getInt(1);
				String movieName = rs.getString(2);
				int categoryNum = rs.getInt(3);
				String category = changeCategory(categoryNum);
				int runtime = rs.getInt(4);
				String img = rs.getString(5);
				
				MovieVO vo = new MovieVO(movieNo, movieName, category, runtime, img);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ArrayList<ScheduleVO> getScheduleList(String outMovieName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		
		try {
			String sql = "SELECT s.schNo, m.movieName, s.runDay, s.roomNo, r.seatCnt FROM schedule s, movie m, room r WHERE s.movieNo = m.movieNo AND s.schNo = r.schNo AND m.movieName = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outMovieName);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int schNo = rs.getInt(1);
				String movieName = rs.getString(2);
				Timestamp runDay = rs.getTimestamp(3);
				int roomNo = rs.getInt(4);
				int seatCnt = rs.getInt(5);
				
				ScheduleVO vo = new ScheduleVO(schNo, movieName, runDay, roomNo, seatCnt);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public ArrayList<Integer> getRemainSeat(int schNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		try {
			String sql = "SELECT t.seatNo FROM schedule s, ticket t WHERE s.schNo = ? AND s.schNo = t.schNo";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public int updateRoomInfo(int schNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "UPDATE room SET seatCnt = seatCnt + 1 WHERE schNo = ?";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pstmt, conn);
		}
		
		return result;
	}
	
	public int reservationTicket(int schNo, int seatNo, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String sql = "INSERT INTO ticket VALUES(?, SYSDATE, ?, ?, ?)";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxTicketNo());
			pstmt.setInt(2, schNo);
			pstmt.setInt(3, seatNo);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(null, pstmt, conn);
		}
		
		return result;
	}
	
	private int maxTicketNo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int max = 0;
		
		try {
			String sql = "SELECT MAX(ticketNo) FROM ticket";
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				max = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return max;
	}
	
	private String changeCategory(int categoryNum) {
		String category = null;
		
		if(categoryNum == 1) {
			category = "액션";
		} else if(categoryNum == 2) {
			category = "로맨스";
		} else if(categoryNum == 3) {
			category = "코미디";
		} else if(categoryNum == 4) {
			category = "스릴러";
		} else if(categoryNum == 5) {
			category = "애니메이션";
		}
		
		return category;
	}
}