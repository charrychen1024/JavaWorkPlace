package seqqueue;

public class TestQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {23,45,3,7,9,945};
		IQueue<Integer> queue = new SeqQueue<Integer>(Integer.class,data.length+1);
		//入队
		System.out.println("*****入队操作*****");
		for(int i=0;i<data.length;i++){
			queue.enqueue(data[i]);
			System.out.println(data[i]+" 入队");
		}
	//	queue.enqueue(111);
		System.out.println("队列为满："+queue.isFull());
		//出队
		int size = queue.size();
		System.out.println("size = "+size);
		System.out.println("*****出队操作*****");
		for(int i=0;i<size;i++){
			
			System.out.println(queue.dequeue()+" 出队");
		}
		
		System.out.println("队列为空："+queue.isEmpty());

	}

}
