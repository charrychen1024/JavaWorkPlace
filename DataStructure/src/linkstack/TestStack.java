package linkstack;

import seqstack.IStack;

public class TestStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {23,45,3,7,9,945};
		IStack<Integer> stack = new LinkStack<Integer>();
		//入栈
		System.out.println("*****入栈操作*****");
		for(int i = 0;i < data.length;i++){
			stack.push(data[i]);
			System.out.println(data[i]+" 入栈");
		}
		//取栈顶元素
		System.out.println("peek = "+stack.peek());
		int size = stack.size();
//		System.out.println("size = "+size);
		//出栈操作
		System.out.println("*****出栈操作*****");
		for(int i = 0;i < size;i++){
			System.out.println(stack.pop()+" 出栈");
		}
		

	}

}
