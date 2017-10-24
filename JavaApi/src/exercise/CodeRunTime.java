package exercise;

public class CodeRunTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long startTime = System.currentTimeMillis();
		int count = 0;
		for(int i=1;i<=100;i++){
			if(i%3==0){
				try{
					Thread.sleep(10);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				count++;
			}
				
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Number of 1-100 can be divided with 3: "+count);
		System.out.println("Time used: "+(endTime-startTime)+" millisecond");
	}

}
