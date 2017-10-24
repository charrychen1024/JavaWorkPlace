package exercise;

public class CartTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Volunteers v = new Volunteers();
		v.addVolunteer(new Student("01","ZhangSan","Computer","Good"));
		v.addVolunteer(new Student("02","LiSi","Management","Perfect"));
		v.getVolunteer();
	}

}
