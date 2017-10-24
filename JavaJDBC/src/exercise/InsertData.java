package exercise;
import java.io.*;
import java.sql.*;

import chapter11.DBConnection;

public class InsertData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.connection();
/*		String dbDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/test";
		String dbUser = "root";
		String dbPasswd = "charrychen";*/
		String sql = "insert into student(name,age) values(?,?)";
		try {
//			Class.forName(dbDriver);
//			 Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
			PreparedStatement ps = conn.prepareStatement(sql);
			//设置sql参数
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("请输入学生姓名：");
			String name = in.readLine();
			ps.setString(1, name);
			System.out.println("请输入学生年龄：");
			int age = Integer.parseInt(in.readLine());
			ps.setInt(2, age);
			ps.executeUpdate();
			System.out.println("添加成功");
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
