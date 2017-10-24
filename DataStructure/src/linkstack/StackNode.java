package linkstack;

public class StackNode <E>{
	private E data;
	private StackNode<E> next;
	public StackNode(){
			
	}
	public StackNode(E data,StackNode<E> next){
		super();
		this.data = data;
		this.next = next;
	}
	//数据域get属性
	public E getData(){
		return data;
	}
	//数据域set属性
	public void setData(E data){
		this.data = data;
	}
	//引用域get属性
	public StackNode<E> getNext(){
		return next;
	}
	//引用域set属性
	public void setNext(StackNode<E> next){
		this.next = next;
	}

}
