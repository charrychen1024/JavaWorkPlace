package exercise;
import java.io.*;
public class RandomAccessFileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student("ZhangSan",20);
		Student s2 = new Student("LiSi",19);
		Student s3 = new Student("WangWu",24);
		try{
			RandomAccessFile ra = new RandomAccessFile("D:\\School\\JAVA\\WorkSpace\\JavaIO\\2.txt","rw");
			ra.write(s1.name.getBytes());
			ra.write(s1.age);
			ra.write(s2.name.getBytes());
			ra.write(s2.age);
			ra.write(s3.name.getBytes());
			ra.write(s3.age);
			ra.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		RandomAccessFile raf;
		try{
			raf = new RandomAccessFile("D:\\School\\JAVA\\WorkSpace\\JavaIO\\2.txt","r");
			int len = 8;
			raf.skipBytes(12);
			
			System.out.println("第二个学生的信息:");
			String  str = "";
			for(int i=0;i<len;i++){
				str = str+(char)raf.readByte();
			}
			System.out.println("姓名: "+str);
			System.out.println("年龄: "+raf.readInt());
			
			System.out.println("第一个学生的信息:");
			raf.seek(0);
			str = "";
			for(int i=0;i<len;i++){
				str = str+(char)raf.readByte();
			}
			System.out.println("姓名: "+str);
			System.out.println("年龄: "+raf.readInt());
			
			System.out.println("第三个学生的信息:");
			raf.skipBytes(12);
			str = "";
			for(int i=0;i<len;i++){
				str = str+(char)raf.readByte();
			}
			System.out.println("姓名: "+str);
			System.out.println("年龄: "+raf.readInt());
			raf.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}


