package exercise;
import java.io.*;
import java.net.*;
public class EchoServerThread extends Thread{
	private Socket connection;
	public EchoServerThread(Socket _connection){
		connection = _connection;
		start();
	}
	public void run(){
		try{
			DataInputStream in = new DataInputStream(
					connection.getInputStream());
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			String line = new String("");
			while(!line.equalsIgnoreCase(".QUIT")){
				line = in.readUTF();
				System.out.println("Echo "+line);
				out.writeUTF(line);
			}
			in.close();
			out.close();
			connection.close();
			System.out.println("Connection closed");
		}catch(IOException e){
			e.getStackTrace();
		}
	}

}
