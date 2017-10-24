package exercise;

public class Student {
	private String stuNo;
	private String stuName;
	private String department;
	private String english;
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public Student(String stuNo, String stuName, String department,
			String english) {
//		super();
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.department = department;
		this.english = english;
	}

}
