package exercise;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class UpdatePanel extends JPanel implements ActionListener{
	private Connection conn = null;
	private Statement stmt = null;
	private JTextField sqlcommand;
	private JButton submit;
	private JTextArea resultarea;
	private ResultSet rs = null;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/test";
	private String dbuser = "root";
	private String dbpwd = "charrychen";
	public UpdatePanel(){
		sqlcommand = new JTextField(30);
		resultarea = new JTextArea(5,30);
		resultarea.setEditable(false);
		submit = new JButton("提交");
		add(new JLabel("SQL更新语句"));
		add(sqlcommand);
		add(submit);
		add(new JLabel("当前数据表中的记录"));
		JScrollPane scrollpane = new JScrollPane(resultarea);
		add(scrollpane);
		submit.addActionListener(this);
		try{
//			DriverManager.registerDriver(new org.git.mm.mysql.Driver());
			Class.forName(driver);
			conn = DriverManager.getConnection(url,dbuser,dbpwd);
			System.out.println("已连接到数据库");
			stmt = conn.createStatement();
			showTable();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
	}
	public void showTable(){
		try{
			rs = stmt.executeQuery("SELECT * FROM student");
			resultarea.setText("姓名");
			for(int i = 1;i <= (30 - 2*"姓名".length());i++){
				resultarea.append(" ");
			}
			resultarea.append("年龄"+"\n");
			while(rs.next()){
				String sname = rs.getString("name");
				resultarea.append(sname);
				int length = sname.length();
				for(int i=1;i<=(30-2*"姓名".length());i++){
					resultarea.append(" ");
				}
				resultarea.append(rs.getString("age")+"\n");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void actionPerformed(ActionEvent ae){
		try{
			String command = sqlcommand.getText();
			command = new String(command.getBytes(),"gbk");
			stmt = conn.prepareStatement("SET NAMES 'gbk';");
			stmt.executeUpdate("SET character_set_client = gbk");
			stmt.executeUpdate("SET character_set_server = gbk");
			stmt.executeUpdate("SET character_set_connection = gbk");
			stmt.executeUpdate("SET character_set_database = gbk");
			stmt.executeUpdate("SET character_set_results = gbk");
			stmt.execute(command);
			showTable();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
