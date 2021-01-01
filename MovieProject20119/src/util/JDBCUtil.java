package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pass = "hr";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) { try {rs.close();} catch(Exception e) {e.printStackTrace();} }
		if(pstmt != null) { try {pstmt.close();} catch(Exception e) {e.printStackTrace();} }
		if(conn != null) { try {conn.close();} catch(Exception e) {e.printStackTrace();} }
	}
}
