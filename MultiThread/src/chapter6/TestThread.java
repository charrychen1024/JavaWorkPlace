package chapter6;

public class TestThread extends Thread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println(Thread.currentThread().getName()+"正在运行");
		}

	}

}
