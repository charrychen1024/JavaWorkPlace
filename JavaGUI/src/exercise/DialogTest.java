package exercise;
import java.io.File;

import javax.swing.*;
public class DialogTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		customerDialog1();
//		customerDialog2();
//		customerDialog3();
//		customerDialog4();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("c:\\"));
		chooser.showOpenDialog(null);

	}
	public static void customerDialog1(){
		Object[] message = new Object[4];
		message[0] = "Please input a new password";
		message[1] = new JPasswordField();
		message[2] = "Please confirm the password";
		message[3] = new JPasswordField();
		String[] options = {"Yes","No"};
		int result = JOptionPane.showOptionDialog(null, message, "Update password", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(result==JOptionPane.OK_OPTION){
			
		}
	}

}
