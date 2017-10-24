package exercise;

public class ThreadTest {
	public static void main(String args[]){
		System.out.println("主线程开始");
		CountingThread thread1 = new CountingThread();
		thread1.start();
		CountingThread thread2 = new CountingThread();
		thread2.start();
		CountingThread thread3 = new CountingThread();
		thread3.start();
	}

}
