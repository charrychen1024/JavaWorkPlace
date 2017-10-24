package exercise;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class LocalHost extends JApplet{
	InetAddress hostAddr = null;
	public void start(){
		try{
			hostAddr = InetAddress.getLocalHost();
		}catch(UnknownHostException e){
			System.err.println(e.getMessage());
		}
		repaint();
	}
	public void paint(Graphics g){
		g.drawString("Host name/ip = "+hostAddr.toString(), 10, 30);
		g.drawString("Host name = "+hostAddr.getHostName(), 10, 40);
		g.drawString("Host ip = "+hostAddr.getHostAddress(), 10, 50);
	}

}
