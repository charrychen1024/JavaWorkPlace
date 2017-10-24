package LinkQueue;

import seqqueue.IQueue;

public class LinkQueue<E> implements IQueue<E> {
	private QueueNode<E> front;
	private QueueNode<E> rear;
	private int maxsize;
	private int size;
	public LinkQueue(){
		front = rear = null;
		size = maxsize = 0;
	}
	public LinkQueue(int maxsize){
		super();
		this.maxsize = maxsize;
	}

	@Override
	public boolean enqueue(E item) {
		// TODO Auto-generated method stub
		QueueNode<E> newNode = new QueueNode<E>(item);
		if(!isFull()){
			if(isEmpty()){
				front = newNode;
				rear = newNode;
			}
			else{
				rear.setNext(newNode);
				rear = newNode;
			}
			size++;
			return true;
		}else
		return false;
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) 
			return null;
		QueueNode<E> node = front;
		front = front.getNext();
		if(front==null)
			rear = null;
		size--;
		return node.getData();
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(!isEmpty())
			return front.getData();
		else
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size == maxsize;
	}
	

}
