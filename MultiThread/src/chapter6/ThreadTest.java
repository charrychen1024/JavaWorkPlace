package chapter6;

public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestThread test = new TestThread();
//		Thread t = new Thread(test);
		test.start();
		while(true){
			System.out.println("主线程正在运行");
		}

	}

}
