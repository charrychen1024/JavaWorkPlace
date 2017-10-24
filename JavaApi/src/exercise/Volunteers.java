package exercise;
import java.util.*;
public class Volunteers {
	private ArrayList list = new ArrayList();
	public void addVolunteer(Student stu){
		list.add(stu);
	}
	public void getVolunteer(){
		int stuCount = 0;
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Student stu = (Student)iterator.next();
			System.out.println("No: "+stu.getStuNo()+", Name: "+stu.getStuName()+", Department: "
					+stu.getDepartment()+", English: "+stu.getEnglish());
			stuCount++;
		}
		System.out.println("Total number of volunteers: "+stuCount);
	}
}
