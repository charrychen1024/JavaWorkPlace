package singlelinklist;

import java.util.Scanner;

import seqlist.ILinearList;

public class TextList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ILinearList<Integer> list = new SLinkList<Integer>();
		int[] data = {23,45,3,7,6,945};
		Scanner sc = new Scanner(System.in);
		System.out.println("*******************");
		System.out.println("操作选项菜单");
		System.out.println("1.添加元素");
		System.out.println("2.插入元素");
		System.out.println("3.删除元素");
		System.out.println("4.定位元素");
		System.out.println("5.取表元素");
		System.out.println("6.显示线性表");
		System.out.println("0.退出");
		System.out.println("*******************");
		char ch;
		do{
			System.out.println("请输入操作选项");
			ch = sc.next().charAt(0);
			switch(ch){
			case '1':
				for(int i = 0;i < data.length;i++)
					list.add(data[i]);
				System.out.println("添加成功");
				break;
			case '2':
				System.out.println("请输入要插入的位置");
				int loc = sc.nextInt();
				System.out.println("请输入要插入的值");
				int num = sc.nextInt();
				list.add(loc-1,num);
				System.out.println("插入成功");
				break;
			case '3':
				 System.out.println("请输入要删除元素的位置");
				 loc = sc.nextInt();
				 list.remove(loc);
				 System.out.println("删除成功");
				 break;
			case '4':
				 System.out.println("请输入要查找的元素");
				 num = sc.nextInt();
				 System.out.println(num+"在列表中的位置为："+(list.indexOf(num)));
				 break;
			case '5':
				 System.out.println("请输入要查找元素的位置");
				 loc = sc.nextInt();
				 System.out.println(loc+"位置上的元素为："+(list.get(loc-1)));
				 break;
			case '6':
				 System.out.println("线性表中的元素有：");
				 for(int i = 0;i<list.size();i++){
					 System.out.print(list.get(i)+" ");
				 }
				 System.out.println();
				 break;
			}	
		}while(ch != '0');
		System.out.println("退出成功");
		sc.close();
		
		// equals 和 == 的区别
		System.out.println("*****equal和==的区别*****");
		int i = 1;
		int j = 1;
		char c1 = 'a';
		char c2 = 'a';
		String str1 = "abc";
		String str2 = "abc";
		String str3 = new String("abc");
		String str4 = new String("abc");
		// ==
		System.out.println(i == j);
		System.out.println(c1 == c2);
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str3 == str4);
		// equals
		System.out.println(str1.equals(str2));
		System.out.println(str1.equals(str3));
		System.out.println(str3.equals(str4));
	}

}
