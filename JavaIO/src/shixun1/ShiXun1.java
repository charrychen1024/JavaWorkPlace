package shixun1;
import java.io.*;
public class ShiXun1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomAccessFile raf;
		try{
			raf = new RandomAccessFile("1.txt","r");
			int beginIndex = 0;
			raf.seek(beginIndex);
			byte[] bt = new byte[10];
			int br = 0;
			while((br=raf.read(bt))!=-1){
//				System.out.write(bt,0,br);
				String str = new String(bt,0,br);
				System.out.println(str);
			}
			raf.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
