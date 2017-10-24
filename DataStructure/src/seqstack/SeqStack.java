package seqstack;

import java.lang.reflect.Array;

public class SeqStack<E> implements IStack<E> {
	private int maxsize;
	private E[] data;
	private int top;
	
	//初始化栈
	@SuppressWarnings("unchecked")
	public SeqStack(Class<E> type,int size){
		data = (E[])Array.newInstance(type, size);
		maxsize = size;
		top = -1;
	}
	//入栈
	@Override
	public E push(E item) {
		// TODO Auto-generated method stub
		if(!isFull()){
			data[++top] = item;
			return item;
		}
		else
		return null;
	}
	//判断栈是否为满
	private boolean isFull() {
		// TODO Auto-generated method stub
		return top == maxsize - 1;
	}
	//出栈
	@Override
	public E pop() {
		// TODO Auto-generated method stub
		E item = null;
		if(!isEmpty()){
			item = data[top--];
			
		}
		return item;
		//return null;
	}
    //取栈顶元素
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		E item = null;
		if(!isEmpty()){
			item = data[top];
			return item;
		}
		return null;
	}
    //求栈长度
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return top + 1;
	}
    //判断栈是否为空
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return top == -1;
	}
	

}
