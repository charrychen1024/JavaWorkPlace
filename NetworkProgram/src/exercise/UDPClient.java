package exercise;
import java.net.*;
import java.io.*;
public class UDPClient {
	public static void main(String[] args){
		try{
			DatagramSocket sendSocket = new DatagramSocket();
			String string = "Hello, world!";
			byte[] databyte = new byte[100];
			databyte = string.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(databyte,string.length(),
					InetAddress.getByName("localhost"),5678);
			sendSocket.send(sendPacket);
			System.out.print("Sending data packet...\t");
			System.out.println(string);
		}catch(SocketException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
