package exercise;
import java.util.*;
public class HashTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable hash = new Hashtable(2,(float)0.8);
		hash.put("BeiJing", "BeiJing");
		hash.put("LiaoNing", "ShengYang");
		hash.put("ShanDong", "JiNan");
		System.out.println("Component of hash table: "+hash);
		System.out.println("Element number: "+hash.size());
		Enumeration enum1 = hash.elements();
		System.out.print("Element = ");
		while(enum1.hasMoreElements())
			System.out.print(enum1.nextElement()+" ");
		System.out.println();
		if(hash.containsKey("LiaoNing"))
			System.out.println("Privincial capital of LiaoNing: "+hash.get("LiaoNing"));
		hash.remove("BeiJing");
		System.out.println("Now component of hash table: "+hash);
		System.out.println("Now element number: "+hash.size());

	}

}
