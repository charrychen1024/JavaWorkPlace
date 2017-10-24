package exercise;
import java.sql.*;
import java.util.*;
public class JDBTest {
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement prepstmt = null;
		final String DB_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = 
				"jdbc:mysql://localhost:3306/spider?autoReconnect=true&useUnicode=true";
		final String DB_USER = "root";
		final String DB_PASSWORD = "charrychen";
		try{
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			String sql = "select id,title from lendinginfo_part1 where id <= 10";
			prepstmt = conn.prepareStatement(sql);
			ResultSet result = prepstmt.executeQuery();
			while(result.next()){
				int id = result.getInt(1);
				String title = result.getString(2);
				System.out.println("id = "+id+"; title = "+title);
			}
			conn.close();
			conn = null;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}
