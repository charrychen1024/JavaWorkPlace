package seqqueue;

import java.lang.reflect.Array;

public class SeqQueue<E> implements IQueue<E> {
	private int maxsize;
	private E[] data;
	private int front;
	private int rear;
	
	@SuppressWarnings("unchecked")
	public SeqQueue(Class<E> type, int size){
		data = (E[])Array.newInstance(type, size);
		maxsize = size;
		front = rear = -1;
	}

	@Override
	public boolean enqueue(E item) {
		// TODO Auto-generated method stub
		if(!isFull()){
			rear = (rear+1)%maxsize;
			data[rear] = item;
			return true;
		}else
		return false;
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(!isEmpty()){
			front = (front+1)%maxsize;
			return data[front];
		}else
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(!isEmpty()){
			return data[(front+1)%maxsize];
		}else
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (rear - front + maxsize)%maxsize;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(front == rear)
			return true;
		else
		    return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if(rear-front==maxsize || (rear+1)%maxsize==front)
			return true;
		else
		    return false;
	}

}
