package exercise;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class EventListenerTest {

	/**
	 * @param args
	 */
	private JLabel label = new JLabel("Label");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EventListenerTest().init();

	}
	public void init(){
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		JButton button = new JButton("Button");
//		button.addActionListener(this);
		button.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						label.setText("GOOD");
					}	
				}
				);
		p.add(label);
		p.add(button);
		f.getContentPane().add(p);
		f.setTitle("Window");
		f.setBounds(1200, 600, 300, 400);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
/*	public void actionPerformed(ActionEvent e){
		label.setText("GOOD");
	}*/

}
