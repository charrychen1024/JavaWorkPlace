package exercise;
import java.net.*;
import java.io.*;
public class EchoServer {
	private static boolean running = true;
	public static void main(String[] main){
		int port = 2000;
		try{
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server started on "+server.getLocalPort());
			while(running){
				Socket connection = server.accept();
				System.out.println("Connection established");
				DataInputStream in = new DataInputStream(
						connection.getInputStream());
				DataOutputStream out = new DataOutputStream(
						connection.getOutputStream());
				String line = new String();
				try{
					while(!line.equalsIgnoreCase(".QUIT")){
						line = in.readUTF();
						System.out.println("Echoing: "+line);
						if(line.toUpperCase().equals(".SHUTDOWN")){
							running = false;
							line = ".QUIT";	
						}
						out.writeUTF(line);
					}
					in.close();
					out.close();
					connection.close();
				}catch(IOException e){
					e.getStackTrace();
				}
			}
			System.out.println("Server closed");
		}catch(IOException e){
			e.getStackTrace();
		}
	}

}
