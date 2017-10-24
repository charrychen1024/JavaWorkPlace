package exercise;
import java.io.*;
public class IOStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FileInputStream fis;
//		FileOutputStream fos;
		FileReader fr;
		FileWriter fw;
		try{
/*			fis = new FileInputStream("1.txt");
			fos = new FileOutputStream("1copy.txt");			
			int bt = 0;
			while((bt=fis.read())!=-1){
				System.out.println(bt);
				System.out.println((char)bt);
				fos.write(bt);
			}
			fis.close();
			fos.close();*/
			fr = new FileReader("1.txt");
			fw = new FileWriter("1copy.txt");
			int len = 0;
			char[] buf = new char[1024];
			while((len = fr.read(buf))!=-1){			
				String str = new String(buf,0,len);
				System.out.println(str);
				fw.write(buf,0,len);
			}
			fr.close();
			fw.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
