package exercise;
import java.util.Date;
public class ShowSeconds implements Runnable {
	private Thread clocker = null;
	private Date now = new Date();
	public ShowSeconds(){
		clocker = new Thread(this);
		clocker.start();
	}
	public void run(){
		while(true){
			now = new Date();
			System.out.println(now);
			try{
				clocker.sleep(10000);
			}catch(InterruptedException ie){
				System.err.println("Thread error: "+ie);
			}
		}
	}
	public static void main(String[] args){
		ShowSeconds ss = new ShowSeconds();
	}

}
