package seqlist;

import java.lang.reflect.Array;

public class SeqList <T> implements ILinearList<T>{
	private int maxsize; //顺序表最大长度
	private T[] data;  //存放数据的数组
	private int size; //顺序表实际长度
	//初始化线性表
	public SeqList(Class<T> type, int maxsize){
		this.maxsize = maxsize;
		data = (T[])Array.newInstance(type, maxsize);
		size = 0;
	}

	@Override
	public boolean add(T item) {
		// TODO Auto-generated method stub
		if(!isFull()){
			data[size++] = item;
			return true;
		}else
		return false;
	}

	@Override
	public boolean add(int index, T item) {
		// TODO Auto-generated method stub
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		if(!isFull()){
			for(int j = size - 1;j >= index;j--){
				data[j+1] = data[j];
			}
			data[index] = item;
			size++;
			return true;
		}else
		return false;
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		if(!isEmpty()){
			T oldValue = data[index];
			for(int j = index;j < size-1;j++)
				data[j] = data[j+1];
			data[--size] = null;
			return oldValue;
		}else
		return null;
	}

	@Override
	public int indexOf(T item) {
		// TODO Auto-generated method stub
		if(item == null){
			for(int i = 0;i < size; i++)
				if(data[i] == null)
					return i;
		}else{
			for(int i = 0; i < size;i++)
				if(item.equals(data[i]))
					return i;
		}
		return -1;
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		return data[index];
	}

	@Override
	public T set(int index, T item) {
		// TODO Auto-generated method stub
		rangeCheck(index);
		T oldValue = data[index];
		data[index] = item;
		return oldValue;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i = 0;i < size;i++){
			data[i] = null;
		}
		size = 0;
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	//检查索引范围
	private void rangeCheck(int index) {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: "+index+", size: "+size);
		
	}
	//判断顺序表是否为满
	private boolean isFull(){
		return size == maxsize;
	}

}
