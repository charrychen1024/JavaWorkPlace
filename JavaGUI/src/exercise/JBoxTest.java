package exercise;
import java.awt.*;
import javax.swing.*;
public class JBoxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JLabel lblTitle = new JLabel("Sing up",JLabel.CENTER);
		JLabel lblUserName = new JLabel("Name",JLabel.LEFT);
		JTextField txtUserName = new JTextField(14);
		JLabel lblPassword = new JLabel("Password",JLabel.LEFT);
		JPasswordField pwdPassword = new JPasswordField(20);
		JLabel lblSex = new JLabel("Gender",JLabel.LEFT);
		JComboBox cmbSex = new JComboBox();
		cmbSex.addItem("Male");
		cmbSex.addItem("Female");
		JLabel lblRemark = new JLabel("Remark",JLabel.LEFT);
		JTextArea taRemark = new JTextArea(4,20);
		JScrollPane scroll = new JScrollPane(taRemark);
		
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(lblTitle,BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1));
		JPanel pUserName = new JPanel();
		pUserName.setLayout(new FlowLayout(FlowLayout.LEADING));
		pUserName.add(lblUserName);
		pUserName.add(txtUserName);
		
		JPanel pPassword = new JPanel();
		pPassword.setLayout(new FlowLayout(FlowLayout.LEADING));
		pPassword.add(lblPassword);
		pPassword.add(pwdPassword);
		
		JPanel pSex = new JPanel();
		pSex.setLayout(new FlowLayout(FlowLayout.LEADING));
		pSex.add(lblSex);
		pSex.add(cmbSex);
		
		JPanel pRemark = new JPanel();
		pRemark.setLayout(new FlowLayout(FlowLayout.LEADING));
		pRemark.add(lblRemark);
		pRemark.add(scroll);
		
		p.add(pUserName);
		p.add(pPassword);
		p.add(pSex);
		p.add(pRemark);
		
		contentPane.add(p,BorderLayout.CENTER);
		f.setSize(300,350);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
