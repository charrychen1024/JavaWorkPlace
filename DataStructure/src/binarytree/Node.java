package binarytree;

public class Node<E> {
	private E data;
	private Node<E> lchild;
	private Node<E> rchild;
	//构造函数
	public Node(E val,Node<E> lp,Node<E> rp){
		data = val;
		lchild = lp;
		rchild = rp;
	}
	
	public Node(Node<E> lp,Node<E> rp){
		this(null,lp,rp);
	}
	
	public Node(E val){
		this(val,null,null);
	}
	
	public Node(){
		this(null);
	}
	//数据属性
	public E getData(){
		return data;
	}
	public void setData(E data){
		this.data = data;
	}
	//左孩子
	public Node<E> getLChild(){
		return lchild;
	}
	public void setLChild(Node<E> lchild){
		this.lchild = lchild;
	}
	//右孩子
		public Node<E> getRChild(){
			return rchild;
		}
		public void setRChild(Node<E> rchild){
			this.rchild = rchild;
		}
}
