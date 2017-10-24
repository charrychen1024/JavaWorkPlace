package exercise;
import java.net.*;
import java.io.*;
import java.util.*;
public class EchoClient {
	public static void main(String[] args) throws IOException{
//		if(args.length!=1){
//			System.out.println("Usage: java EchoClient <hostname>");
//			return;
//		}
		//从控制台输入主机名
		System.out.println("Please input the hostname");
		Scanner sc = new Scanner(System.in);
		String hostname = sc.next();
		//获得一个DatagramSocket
		DatagramSocket socket = new DatagramSocket();
		//发送请求
		String str = "I want to log in!!!";
		byte[] buf = new byte[256];
		buf = str.getBytes();
		InetAddress address = InetAddress.getByName(hostname);
		DatagramPacket packet = new DatagramPacket(buf,buf.length,address,4248);
		socket.send(packet);
		//获取回应信息
//		try{
//			System.out.println("勇于创新");
//			byte[] buf2 = new byte[256];
//			packet = new DatagramPacket(buf2,buf2.length);
//		}catch(Exception e){
//			System.out.println("创新失败，走老路子");
//			packet = new DatagramPacket(buf,buf.length);
//		}
		System.out.println("勇于创新");
		byte[] buf2 = new byte[256];
		packet = new DatagramPacket(buf2,buf2.length);
		socket.receive(packet);
		int port = packet.getPort();
		System.out.println("Server port: "+port);
		//显示回应信息
		String received = new String(packet.getData());
		System.out.println("Reponsed: "+received);
		socket.close();
	}

}
