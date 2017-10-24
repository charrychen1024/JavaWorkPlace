package linkstack;

import seqstack.IStack;

public class LinkStack<E> implements IStack<E> {
	private StackNode<E> top = null;
	private int size = 0;

	@Override
	public E push(E item) {
		// TODO Auto-generated method stub
		StackNode<E> newNode = new StackNode<E>(item,null);
		if(!empty())
			newNode.setNext(top);
		top = newNode;
		size++;
		return item;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		E item = null;
		if(!empty()){
			item = top.getData();
			top = top.getNext();
			size--;
		}
		return item;
	}

	private boolean empty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return top.getData();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
