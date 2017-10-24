package exercise;
import java.util.*;
public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		map1.put("01", "zhangsan");
		map1.put("02", "lisi");
		map2.put("network", "Computer");
		map2.put("information management", "Management");
		System.out.println("map1.get(\"1\")="+map1.get("01"));
		System.out.println("map1.remove(\"1\")="+map1.remove("01"));
		System.out.println("map1.get(\"1\")="+map1.get("01"));
		
		map1.putAll(map2);
		map2.clear();
		System.out.println("map1 is empty:"+map1.isEmpty());
		System.out.println("map2 is empty:"+map2.isEmpty());
		System.out.println("map1 number of key-value:"+map1.size());
		System.out.println("map1 all key:"+map1.keySet());
		System.out.println("map1 all value:"+map1.values());
		System.out.println("map1 map:"+map1.entrySet());
		System.out.println("map1 includes key\"computer\":"+map1.containsKey("computer"));
		System.out.println("map2 includes vaule\"Management\":"+map2.containsValue("Management"));

	}

}
