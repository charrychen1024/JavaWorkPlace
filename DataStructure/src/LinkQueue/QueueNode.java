package LinkQueue;

import linkstack.StackNode;

public class QueueNode<E> {
	private E data;
	private QueueNode<E> next;
	public QueueNode(){};
	public QueueNode(E data){
		this.data = data;
	}
	public QueueNode(E data,QueueNode<E> next){
		this.data = data;
		this.next = next;
	}

	// 数据域get属性
	public E getData() {
		return data;
	}

	// 数据域set属性
	public void setData(E data) {
		this.data = data;
	}

	// 引用域get属性
	public QueueNode<E> getNext() {
		return next;
	}

	// 引用域set属性
	public void setNext(QueueNode<E> next) {
		this.next = next;
	}

}
