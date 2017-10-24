package exercise;
import java.net.*;
import java.io.*;
public class WebConnect {
	public static void main(String[] args){
		String hostname = "www.baidu.com";
		try{
			
			Socket connection = new Socket(hostname,80);
			System.out.println("Connection established:");
			System.out.println("Local Connection Information:");
			System.out.println("\t address: "+connection.getLocalAddress());
			System.out.println("\t prot: "+connection.getLocalPort());
			System.out.println("Remote Connection Information");
			System.out.println("\t address: "+connection.getInetAddress());
			System.out.println("\t prot: "+connection.getPort());
			connection.close();
		}catch(UnknownHostException uhe){
			System.out.println("Unkonwn host: "+hostname);
		}catch(IOException ioe){
			System.out.println("IOException: "+ioe);
		}
	}

}
