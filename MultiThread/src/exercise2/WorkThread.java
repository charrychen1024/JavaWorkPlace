package exercise2;

public class WorkThread extends Thread{
	private CorruptedData data = null;
	private int work = 0;
	public WorkThread(CorruptedData _data,int _work){
		data = _data;
		work = _work;
		start();
	}
	public void run(){
		data.performWork(work);
	}
}

