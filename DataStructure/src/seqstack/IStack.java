package seqstack;

public interface IStack<E> {
	E push(E item); //入栈
	E pop(); //出栈
	E peek(); //取栈顶元素
	int size(); //元素个数
	boolean isEmpty(); //判断栈是否为空

}
