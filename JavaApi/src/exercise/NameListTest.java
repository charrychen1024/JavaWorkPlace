package exercise;
import java.util.*;
public class NameListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List array = new ArrayList();
		Scanner in = new Scanner(System.in);
		for(int i=0;i<5;i++){
			System.out.print("请输入第"+(i+1)+"个人的姓名：");
			String name = in.next();
			array.add(name);
		}
		for(int i=0;i<array.size();i++){
			System.out.println("第"+(i+1)+"个元素是："+array.get(i));
		}

	}

}
