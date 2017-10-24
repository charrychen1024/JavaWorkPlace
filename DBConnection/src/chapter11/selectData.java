package chapter11;
import java.sql.*;
public class selectData {

	/**
	 * @param args
	 */
	static String url = "jdbc:mysql://localhost:3306/test";
	static String user = "root";
	static String passwd = "charrychen";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,passwd);
			Statement st = conn.createStatement();
			String sql = "Select *from student";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String stuName = rs.getString(1);
				int stuAge = rs.getInt("age");
				System.out.println("Name = "+stuName+"  Age = "+stuAge);	
			}
			rs.close();
			st.close();
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
