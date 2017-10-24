package exercise;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class ClickMe extends Applet implements ActionListener{
	private Button quit = new Button("quit");
	private Button click = new Button("Click here");
	private TextField text = new TextField(10);
	private boolean secondClick = false;
	
	public void init(){
		setLayout(new FlowLayout());
		add(quit);
		add(click);
		add(text);
		quit.addActionListener(this);
		click.addActionListener(this);
	}
	public void start(){
		text.setText("Applet started");
	}
	public void stop(){
		text.setText("Applet stopped");
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==quit){
			text.setText("Can not quit appletes");
		}
		else if(ae.getSource()==click){
			if(secondClick)
				text.setText("Not again!");
			else
				text.setText("Uh, it tickles");
			secondClick = !secondClick;
		}
	}

}
