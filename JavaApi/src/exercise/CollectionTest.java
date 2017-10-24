package exercise;
import java.util.*;
public class CollectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection collection = new ArrayList();
		collection.add("第一个对象");
		collection.add("第二个对象");
		collection.add("第三个对象");
		System.out.println("集合collection的大小："+collection.size());
		System.out.println("集合collection的内容："+collection);
		collection.remove("第一个对象");
		System.out.println("集合collection移除第一个对象后的内容："+collection);
		System.out.println("集合collection是否包含第一个对象："+collection.contains("第一个对象"));
		System.out.println("集合collection是否包含第二个对象："+collection.contains("第二个对象"));
		Collection collection2 = new ArrayList();
		collection2.addAll(collection);
		System.out.println("集合collection2的大小："+collection2.size());
		System.out.println("集合collection2的内容："+collection2);
		collection2.clear();
		System.out.println("集合collection2是否为空："+collection2.isEmpty());
		Object s[] = collection.toArray();
		for(int i = 0;i<s.length;i++){
			System.out.println(s[i]);
		}
		

	}

}
