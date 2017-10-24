package exercise;

import javax.swing.*;

public class MenuTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		JMenuBar JMenubar = new JMenuBar();
		JMenu fileM = new JMenu("File");
		JMenu editM = new JMenu("Edit");
		JMenuItem fileMI1 = new JMenuItem("New");
		JMenuItem fileMI2 = new JMenuItem("Open");
		JCheckBoxMenuItem fileMI3 = new JCheckBoxMenuItem("Quit",true);
		JMenu filePrint = new JMenu("print");
		JMenuItem printMI1 = new JMenuItem("preview");
		JMenuItem printMI2 = new JMenuItem("setting");
		
		JMenubar.add(fileM);
		JMenubar.add(editM);
		
		fileM.add(fileMI1);
		fileM.add(fileMI2);
		
		fileM.add(filePrint);
		filePrint.add(printMI1);
		filePrint.add(printMI2);
		fileM.addSeparator();
		fileM.add(fileMI3);
		
		f.setJMenuBar(JMenubar);
//		f.setSize(300,550);
		f.setBounds(1200, 600, 500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
