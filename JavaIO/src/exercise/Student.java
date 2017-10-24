package exercise;

public class Student{
	public String name;
	public int age;
	final static int LEN = 8;
	public Student(String name,int age){
		if(name.length()>LEN){
			name = name.substring(0,8);
		}else{
			while(name.length()<LEN	)
				name = name+"\u0000";
		}
		this.name = name;
		this.age = age;
	}
}
