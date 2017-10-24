package binarytree;

public interface IBiTree<E> {
	void create(E val,Node<E> l,Node<E> r); 
	Node<E> getLChild(Node<E> p);
	Node<E> getRChild(Node<E> p);
	void insertL(E val,Node<E> p);
	void insertR(E val,Node<E> p);
	Node<E> deleteL(Node<E> p);
	Node<E> deleteR(Node<E> p);
	Node<E> search(Node<E> root,E value);
	void traverse(Node<E> root, int i);

}
