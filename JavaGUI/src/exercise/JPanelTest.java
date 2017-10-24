package exercise;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
public class JPanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("Label");
		JTextField textField = new JTextField("TextField");
		JPanel p = new JPanel();
		p.add(label);
		p.add(textField);
		Border border = BorderFactory.createEtchedBorder();
		p.setBorder(border);
		JFrame f = new JFrame();
		f.setTitle("Panel Container");
		f.setSize(200,200);
		Container contentPane = f.getContentPane();
		contentPane.add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
