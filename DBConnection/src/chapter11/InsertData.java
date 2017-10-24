package chapter11;
import java.sql.*;
public class InsertData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DRIVER  = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://127.0.0.1:3306/test";
		final String DBUSER = "root";
		final String DBPASSWORD = "charrychen";
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
			String sql = "Select* from student";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				String name = rs.getString(1);
				int age = rs.getInt(2);
				System.out.println("Name: "+name+"  Age: "+age);
			}
			rs.close();
			state.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
