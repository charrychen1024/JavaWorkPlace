package exercise;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class UpdateFrame extends JFrame{
	public UpdateFrame(){
		setTitle("数据库更新");
		setSize(500,200);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		Container contentpane = getContentPane();
		contentpane.add(new UpdatePanel());
	}
}
