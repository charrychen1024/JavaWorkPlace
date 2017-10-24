package seqqueue;

public interface IQueue<E> {
	boolean enqueue(E item);
	E dequeue();
	E peek();
	int size();
	boolean isEmpty();
	boolean isFull();

}
