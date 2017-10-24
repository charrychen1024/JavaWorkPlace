package exercise;
import java.awt.*;

import javax.swing.*;
public class JBoxTest2 {

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
		JLabel lblEducation = new JLabel("Education",JLabel.LEFT);
		JRadioButton rdCollege = new JRadioButton("College");
		JRadioButton rdBachelor = new JRadioButton("Bachelor");
		JRadioButton rdMaster = new JRadioButton("Master");
		ButtonGroup group = new ButtonGroup();
		group.add(rdCollege);
		group.add(rdBachelor);
		group.add(rdMaster);
		JLabel lblHobby = new JLabel("Hobby",JLabel.LEFT);
		JCheckBox chkSports = new JCheckBox("Sport");
		JCheckBox chkReading = new JCheckBox("Reading");
		JCheckBox chkMovie = new JCheckBox("Movie");
		JLabel lblMajor = new JLabel("Major",JLabel.LEFT);
		Object[] Major = {"Computer","Software","Network"};
		JList lstMajor = new JList(Major);
		JLabel lblRemark = new JLabel("Remark",JLabel.LEFT);
		JTextArea taRemark = new JTextArea(4,20);
		JScrollPane scroll = new JScrollPane(taRemark);
		
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(lblTitle,BorderLayout.NORTH);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(7,1));
		
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
		
		JPanel pMaster = new JPanel();
		pMaster.setLayout(new FlowLayout(FlowLayout.LEADING));
		pMaster.add(lblEducation);
		pMaster.add(rdCollege);
		pMaster.add(rdBachelor);
		pMaster.add(rdMaster);
		
		JPanel pHobby = new JPanel();
		pHobby.setLayout(new FlowLayout(FlowLayout.LEADING));
		pHobby.add(lblHobby);
		pHobby.add(chkSports);
		pHobby.add(chkReading);
		pHobby.add(chkMovie);
		
		JPanel pMajor = new JPanel();
		pMajor.setLayout(new FlowLayout(FlowLayout.LEADING));
		pMajor.add(lblMajor);
		pMajor.add(lstMajor);
		
		JPanel pRemark = new JPanel();
		pRemark.setLayout(new FlowLayout(FlowLayout.LEADING));
		pRemark.add(lblRemark);
		pRemark.add(scroll);
		
		p.add(pUserName);
		p.add(pPassword);
		p.add(pSex);
		p.add(pMajor);
		p.add(pHobby);
		p.add(pMajor);
		p.add(pRemark);
		
		contentPane.add(p,BorderLayout.CENTER);
		f.setSize(300,550);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
