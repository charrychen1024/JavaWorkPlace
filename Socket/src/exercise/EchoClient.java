package exercise;
import java.net.*;
import java.io.*;
public class EchoClient {
	public static void main(String[] args){
		String host = "localhost";
		try{
			Socket conn = new Socket(host,2000);
			System.out.println("Connection established: ");
			DataInputStream in = new DataInputStream(
					conn.getInputStream());
			DataOutputStream out = new DataOutputStream(
					conn.getOutputStream());
			String line = new String("");
			while(!line.toUpperCase().equals(".QUIT")){
				System.out.println("Enter string: ");
				line = readString();
				System.out.println("\t Sending string to server...");
				out.writeUTF(line);
				System.out.println("\t Waiting for server response...");
				line = in.readUTF();
				System.out.println("Received: "+line);
			}
			in.close();
			out.close();
			conn.close();
		}catch(UnknownHostException uhe){
			System.out.println("Unknown host: "+host);
		}catch(IOException ioe){
			System.out.println("IOException: "+ioe);
		}
	}
	public static String readString(){
		String string = new String();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in));
		try{
			string = in.readLine();
		}catch(IOException ioe){
			System.out.println("Console.readString: Unknown error...");
			System.exit(-1);
		}
		return string;
	}

}
