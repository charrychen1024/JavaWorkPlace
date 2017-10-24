package exercise;
import java.applet.Applet;
import java.awt.Graphics;
public class Simple extends Applet{
	StringBuffer buffer;
	public void init(){
		buffer = new StringBuffer();
		addItem("Initializing...");
	}
	public void start(){
		addItem("Starting...");
	}
	public void stop(){
		addItem("Stopping...");
	}
	public void destory(){
		addItem("Preparing for unloading...");
	}
	void addItem(String newWord){
		System.out.println(newWord);
		buffer.append(newWord);
		repaint();
	}
	public void paint(Graphics g){
		g.drawRect(0, 0, size().width-1, size().height-1);
		g.drawString(buffer.toString(), 5, 15);
	}

}
