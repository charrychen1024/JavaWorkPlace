package exercise2;
public class CorruptedData {
	protected static int DISPLAY = 1,CHANGE = 2;
	private WorkThread slowWorker = null;
	private WorkThread fastWorker = null;
	private int number = 0;
	public CorruptedData(){
		number = (int)(10*Math.random());
		slowWorker = new WorkThread(this,DISPLAY);
		fastWorker = new WorkThread(this,CHANGE);
	}
	public synchronized void performWork(int type){
		if(type == DISPLAY){
			System.out.println("Number before sleeping: "+number);
			try{
				slowWorker.sleep(2000);
			}catch(InterruptedException ie){
				System.out.println("Error: "+ie);
			}
			System.out.println("Number after waking up: "+number);
		}
		if(type == CHANGE){
			number = -1;
		}
	}
	public static void main(String[] args){
		CorruptedData cd  = new CorruptedData();
	}

}
