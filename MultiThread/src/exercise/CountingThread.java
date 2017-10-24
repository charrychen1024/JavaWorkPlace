package exercise;

public class CountingThread extends Thread{
	public void run(){
		System.out.println();
		System.out.println("子线程"+this+"开始");
		for(int i = 0;i < 8;i++){
			System.out.print(this.getName()+".i = "+(i+1)+"\t");
		}
		System.out.println();
		System.out.println("子线程"+"结束");
	}

}
