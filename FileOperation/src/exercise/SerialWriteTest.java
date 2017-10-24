package exercise;
import java.io.*;
import java.util.*;
public class SerialWriteTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("sample.dat"));
			int i = 10;
			Date now = new Date();
			Address address = new Address("Java","Java@edu.cn");
			out.writeInt(i);
			out.writeObject(now);
			out.writeObject(address);
			out.close();
		}catch(IOException ioe){
			System.out.println(ioe);
		}

	}

}
