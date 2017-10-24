package chapter5;

public class StudentTest {
	public static void main(String[] args){
		Student stu = new Student("01","ZhangSan",100);
		try{
			stu.addScore(10);
		}catch(AddException e){
			System.out.println(e.toString());
		}
		try{
			stu.addScore(-10);
		}catch(AddException e){
			System.out.println(e.toString());
		}
		stu.subtractScore(150);
	}

}
