package exercise;
import java.io.*;
import java.net.*;
public class EchoServer2 {
	private static boolean running = true;
	public static void main(String[] main){
		int port = 2000;
		try{
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server started on "+server.getLocalPort());
			while(running){
				Socket connection = server.accept();
				System.out.println("New connection moved to thread");
				EchoServerThread handler = new EchoServerThread(connection);
			}
		}catch(IOException e){
			e.getStackTrace();
		}
	}

}
