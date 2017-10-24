package chapter5;

public class Student {
	private String studentNo;
	private String studentName;
	private int studentMorality;
	public Student(String studentNo,String studentName,int studentMorality){
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentMorality = studentMorality;
	}
	//扣分
	public boolean subtractScore(int score){
		if((studentMorality - score)<60){
			try{
				throw new OverSubstractException();
			}catch(OverSubstractException e){
				System.out.println(e.toString());
			}
//			System.out.println("品德分已经很低了");
			return false;
		}
		else{
			studentMorality = studentMorality - score;
			System.out.println("扣分成功");
			return true;
		}
	}
	//加分
	public void addScore(int score) throws AddException{
		if(score <= 0){
			throw new AddException();
		}
		else{
			studentMorality = studentMorality+score;
		}
		
	}

}
