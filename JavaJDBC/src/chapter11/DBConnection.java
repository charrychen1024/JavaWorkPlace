package chapter11;
import java.sql.*;
public class DBConnection {
	public Connection connection(){

		String dbDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/test";
		String dbUser = "root";
		String dbPasswd = "charrychen";
		Connection conn = null;

		try {
			Class.forName(dbDriver);
			 conn = DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
//			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
