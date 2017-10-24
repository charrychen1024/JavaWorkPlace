package singlelinklist;

import seqlist.ILinearList;

public class SLinkList<T> implements ILinearList<T> {
	private Node<T> start;
	int size;
	private static class Node<T>{
		T item;
		Node<T> next;
		Node(T element,Node<T> next){
			this.item = element;
			this.next = next;
		}
	}
	//初始化线性表
	public SLinkList(){
		start = null;
	}
	//添加元素到末尾
	public boolean add(T item){
		if(start == null)
			start = new Node<T>(item,null);
		else
		{
			Node<T> current = start;
			while(current.next != null){
				current = current.next;
			}
			current.next = new Node<T>(item,null);
		}
		size++;
		return true;
	}
	//在单链表的第index索引位置前插入一个元素
	public boolean add(int index,T item){
		Node<T> current;
		Node<T> previous;
		if(index < 0 || index > size)
			return false;
		Node<T> newNode = new Node<T>(item,null);
		//在空链表或第一个元素前插入一个元素
		if(index == 0){
			newNode.next = start;
			start = newNode;
			size++;
		}
		else{//在两个元素之间插入一个元素
			current = start;
			previous = null;
			int j = 0;
			while(current != null && j < index){
				previous = current;
				current = current.next;
				j++;
			}
			if( j == index){
				previous.next = newNode;
				newNode.next = current;
				size++;
			}
			
		}
		return true;
	}
	//删除元素
	public T remove(int index){
		T oldValue = null;
		if(isEmpty() || index < 0 || index > size-1)
			return null;
		Node<T> current = start;
		if(index == 0 ){
			start = current.next;
			oldValue = current.item;
			size--;
		}
		else{
			Node<T> previous = null;
			int j = 1;
			while(current != null && j <= index){
				previous = current;
				current = current.next;
				j++;
			}
			oldValue = current.item;
			previous.next = current.next;
			size--;
		}
		return oldValue;
	}
	//查找元素位置
	public T get(int index){
		T item = null;
		if(isEmpty() || index < 0 || index > size -1 ){
			return null;
		}
		Node<T> current = start;
		int j = 0;
		while(current != null && j < index){
			current = current.next;
			j++;
		}
		if(j == index){
			item = current.item;
		}
		return item;
	}
	//求链表长度
	public int size(){
		return size;
	}
	//清空链表
	public void clear(){
		for(Node<T> x = start;x != null;){
			Node<T> next = x.next;
			x.item = null;
			x.next = null;
			x = next;
		}
		start = null;
		size = 0;
	}
	//判断链表是否为空
	public boolean isEmpty(){
		return size == 0;
	}
	@Override
	public int indexOf(T item) {
		int index = 0;
		if(item == null){
			for(Node<T> x = start;x!=null;x = x.next){
				if(x.item == null)
					return index;
				index++;
			}
		}
		else
		{
			for(Node<T> x = start;x!=null;x = x.next){
				if(item.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}
	@Override
	public T set(int index, T item) {
		// TODO Auto-generated method stub
		return null;
	}

}
