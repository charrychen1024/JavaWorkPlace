package chapter9;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public  class TemperatureTransform {
	private JFrame f = new JFrame();
	private JPanel p = new JPanel();
	private JLabel lblCentigrade = new JLabel("Centigrade");
	private JLabel lblFahrenheit = new JLabel("Fahrenheit");
	private JTextField tfCentigrade = new JTextField(20);
	private JTextField tfFahrenheit = new JTextField(20);
	private JButton bonClick = new JButton("Transform");
	private JPanel pUp = new JPanel();
	private JPanel pDown = new JPanel();
	private JPanel pBon = new JPanel();
	
	public void init(){
		f.setTitle("Temperature Transform");
		f.setBounds(1200, 600, 400, 600);
		f.setVisible(true);
		
		pUp.setLayout(new FlowLayout());
		pUp.add(lblCentigrade);
		pUp.add(tfCentigrade);
		
		pDown.setLayout(new FlowLayout());
		pDown.add(lblFahrenheit);
		pDown.add(tfFahrenheit);
		
		pBon.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBon.add(bonClick);
		
		p.setLayout(new GridLayout(3,1));
		p.add(pUp);
		p.add(pDown);
		p.add(pBon);
		
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bonClick.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(e.getSource()==bonClick){
							Double t = (5*(Double.parseDouble(tfCentigrade.getText())-32))/9.0;
							tfFahrenheit.setText(t.toString());
						}
					}
				});
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TemperatureTransform tt = new TemperatureTransform();
		tt.init();

	}

}
