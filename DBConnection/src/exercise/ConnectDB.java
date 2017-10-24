package exercise;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ConnectDB {
	public static void main(String[] args) {
		JFrame myframe = new ConnectFrame();
		myframe.show();
	}

}
 class ConnectFrame extends JFrame implements ActionListener{
	 private Connection con = null;
	 private Statement stmt = null;
	 private JTextField url = new JTextField(10);
	 private JTextField driver = new JTextField(10);
	 private JTextField username = new JTextField(10);
	 private JPasswordField password = new JPasswordField(10);
	 private JTextArea resultarea = new JTextArea(6,30);
	 private JButton submit = new JButton("连接");
	 private JButton release = new JButton("断开");
	 private JLabel statelabel = new JLabel("数据库连接状态：",SwingConstants.LEFT);
	 private JLabel urllabel = new JLabel("数据库URL：",SwingConstants.LEFT);
	 private JLabel driverlabel = new JLabel("驱动程序：",SwingConstants.LEFT);
	 private JLabel userlabel = new JLabel("用户名: ",SwingConstants.LEFT);
	 private JLabel pwdlabel = new JLabel("密码: ",SwingConstants.LEFT);
	 public ConnectFrame(){
		 setTitle("数据库连接");
		 setSize(840,600);
		 addWindowListener(new WindowAdapter(){
			 public void windowClosing(WindowEvent we){
				 System.exit(0);
			 }
		 });
		resultarea.setEditable(false);
		resultarea.setLineWrap(true);
		Container c = getContentPane();
		c.setLayout(null);
		c.add(urllabel);
		urllabel.setBounds(10, 10, 80, 22);
		c.add(url);
		url.setBounds(80, 10, 240, 22);
		c.add(driverlabel);
		driverlabel.setBounds(10, 40, 80, 22);
		c.add(driver);
		driver.setBounds(80, 40, 240, 22);
		c.add(userlabel);
		userlabel.setBounds(10,70,80,22);
		c.add(username);
		username.setBounds(80,70,240,22);
		c.add(pwdlabel);
		pwdlabel.setBounds(10,100,80,22);
		c.add(password);
		password.setBounds(80,100,240,22);
		c.add(submit);
		submit.setBounds(335,60,80,25);
		c.add(release);
		release.setBounds(335,100,80,25);
		c.add(statelabel);
		statelabel.setBounds(140,135,150,22);
		JScrollPane scrollpane = new JScrollPane(resultarea);
		c.add(scrollpane);
		scrollpane.setBounds(80,160,240,100);
		submit.addActionListener(this);
		release.addActionListener(this);
		driver.setNextFocusableComponent(username);
		password.setNextFocusableComponent(submit);
		submit.setNextFocusableComponent(url);
	 }
	 public void actionPerformed(ActionEvent ae){
		 try{
			 if(ae.getSource()==submit){
			 resultarea.setText("");
			 Class.forName(driver.getText().trim());
			 resultarea.append("驱动程序已加载，即将连接数据库"+"\n");
			 con = DriverManager.getConnection(url.getText().trim(),
					 username.getText().trim(),password.getText().trim());
			 DatabaseMetaData dmd = con.getMetaData();
			 resultarea.append("已连接到数据库："+dmd.getURL()+"\n");
			 resultarea.append("所用的驱动程序: "+dmd.getDriverName()+"\n");
			}
			if(ae.getSource()==release){
				if(!con.isClosed()){
					con.close();
					resultarea.append("数据库已断开连接"+"\n");
				}
				else
					if(resultarea.getText()=="")
					    resultarea.setText("数据库未连接"+"\n");
					else
					    resultarea.append("数据库未连接"+"\n");
			}
		 }catch(Exception e){
			 resultarea.append(e.getMessage());
		 }
	 }
 }