package Parkinglot;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Conn {
	
	private Statement stmt;
	private Connection conn;

	public DB_Conn() {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "parking", "tiger");
			System.out.println("����̹� �ε��� ����!!");
			System.out.println("DB���� ����!!");
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε��� ����!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB���� ����!!");
			e.printStackTrace();
		}
		
	}
	
	public ResultSet excuteQuery(String sql) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public void executeUpdate(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}


	public void executeUpdate1(String deletesql) {
		try {
			stmt.executeUpdate(deletesql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}



	
}
