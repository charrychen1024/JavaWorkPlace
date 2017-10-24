package exercise;
import java.io.*;
public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("D:\\School\\JAVA\\WorkSpace\\JavaIO\\1.txt");
		if(f.exists())
			f.delete();
		else
			try{
				f.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
		System.out.println("File name: "+f.getName());
		System.out.println("File directory: "+f.getPath());
		System.out.println("File absolute directory: "+f.getAbsolutePath());
		System.out.println("File parent directory: "+f.getParent());

	}

}
