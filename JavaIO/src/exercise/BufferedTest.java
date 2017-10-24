package exercise;
import java.io.*;
public class BufferedTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int buffer = 100;
		try{
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream("1.txt"));
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream("1copy.txt"));
			int len = 0;
			byte[] bts = new byte[buffer];
			while((len = bis.read(bts))!=-1){
				String str = new String(bts,0,len);
				System.out.println(str);
				bos.write(bts, 0, len);
			}
			bos.flush();
			bis.close();
			bos.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
