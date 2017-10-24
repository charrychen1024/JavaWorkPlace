package exercise;
import java.io.*;
import java.util.*;
public class SerialReadTest {
	public static void main(String[] args){
		try{
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("sample.dat"));
			int i = in.readInt();
			Date date = (Date)in.readObject();
			Address address = (Address)in.readObject();
			in.close();
			System.out.println("Integer: "+i);
			System.out.println("Date: "+date);
			System.out.println("Address: "+address);
		}catch(ClassNotFoundException cnfe){
			System.out.println(cnfe);
		}catch(IOException ioe){
			System.out.println(ioe);
		}
		
	}

}
