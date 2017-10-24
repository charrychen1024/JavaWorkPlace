package exercise;
import java.io.*;
public class SafeCopy {
	public static void copyFile(DataInputStream in,DataOutputStream out) throws IOException{
		try{
			while(true)
				out.writeByte(in.readByte());
		}catch(EOFException eof){
			return;
		}
	}
	public static void main(String[] args){
		if(args.length != 2)
			System.out.println("Usage: java Copy sourceFile targetFile");
		else{
			String inFileName = args[0],outFileName = args[1];
			File inFile = new File(inFileName);
			File outFile = new File(outFileName);
			if(!inFile.exists())
				System.out.println(inFileName+"does not exit.");
			else if(outFile.exists())
				System.out.println(outFileName+"already exists.");
			else{
				try{
					DataInputStream in = new DataInputStream(
							new BufferedInputStream(
									new FileInputStream(inFileName)));
					DataOutputStream out = new DataOutputStream(
							new BufferedOutputStream(
									new FileOutputStream(outFileName)));
					copyFile(in,out);
					in.close();
					out.close();
				}catch(IOException ioe){
					System.out.println("Unknown error:"+ioe);
				}
			}
		}
	}

}
