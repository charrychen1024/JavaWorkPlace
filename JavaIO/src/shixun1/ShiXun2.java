package shixun1;
import java.io.*;
public class ShiXun2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileWriter fw;
		String fileName = "1.txt";
		String str = "Hello Java";
		try{
			fw = new FileWriter(fileName,true);
//			fw = new FileWriter(fileName);
//			fw.write(str);
			fw.append("C");
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
