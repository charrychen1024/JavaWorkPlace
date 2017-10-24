package seqlist;

public interface ILinearList<T> {
	boolean add(T item);
	boolean add(int index, T item);
	T remove(int index);
	int indexOf(T item);
	T get(int index);
	T set(int index, T item);
	int size();
	void clear();
	boolean isEmpty();

}
