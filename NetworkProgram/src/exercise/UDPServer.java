package exercise;
import java.net.*;
import java.io.*;
public class UDPServer {
	public static void main(String[] args){
		try{
			DatagramSocket receiveSocket = new DatagramSocket(5678);
			byte buf[] = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(buf,buf.length);
			System.out.println("Starting receive data packet");
			while(true){
				receiveSocket.receive(receivePacket);
				String hostname = receivePacket.getAddress().toString();
				System.out.println("From host: "+hostname+"\tProt: "+receivePacket.getPort());
				String s = new String(receivePacket.getData(),0,receivePacket.getLength());
				System.out.println("Recived: "+s);
			}
		
		}catch(SocketException e){
			e.printStackTrace();
			System.exit(1);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
