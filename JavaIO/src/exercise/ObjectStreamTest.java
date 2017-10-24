package exercise;
import java.io.*;
public class ObjectStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student2 stu1 = new Student2("01","ZhangSan","Computer","good");
		Student2 stu2 = new Student2("02","LiSi","Computer","better");
		try{
			FileOutputStream fos = new FileOutputStream("student.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(stu1);
			oos.writeObject(stu2);
			oos.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		stu1 = null;
		stu2 = null;		 
		try {
			FileInputStream	fis = new FileInputStream("student.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			stu1 = (Student2)ois.readObject();
			stu2 = (Student2)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("No: "+stu1.getStuNo());
		System.out.println("Name: "+stu1.getStuName());
		System.out.println("Department: "+stu1.getDepartment());
		System.out.println("English: "+stu1.getEnglish());
		System.out.println("No: "+stu2.getStuNo());
		System.out.println("Name: "+stu2.getStuName());
		System.out.println("Department: "+stu2.getDepartment());
		System.out.println("English: "+stu2.getEnglish());		
	}

}
