package exercise;
import java.net.*;
public class UDPScan {
	public static void main(String[] args){
		for(int port = 1024;port <= 65535;port++){
			try{
				DatagramSocket server = new DatagramSocket(port);
				server.close();
			}catch(SocketException e){
				System.out.println("There is a server in Port: "+port);
			}
		}
	}

}
