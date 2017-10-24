package exercise;
import java.net.*;
import java.io.*;
import java.util.*;
public class EchoServer {
	public static void main(String[] args) throws IOException{
		new EchoServerThread().start();
	}
}

class EchoServerThread extends Thread{
	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
	protected boolean hasMoreWork = true;
	protected int number = 0;
	public EchoServerThread() throws IOException{
		this("EchoServerThread");
	}
	public EchoServerThread(String name) throws IOException{
		super(name);
		socket  = new DatagramSocket(4248);
	}
	public void run(){
		while(hasMoreWork){
			try{
				byte[] buf = new byte[256];
				//接收请求
				DatagramPacket packet = new DatagramPacket(buf,buf.length);
				socket.receive(packet);
				String apply = new String(packet.getData());
				//显示请求
				System.out.println("Message from client: "+apply);
				//创建应答
//				byte[] buf2 = new byte[256];
				String dString = null;
				number++;
				dString = "Hi, You are the first "+number+" visitor!";
				buf = dString.getBytes();
				//将应答发送给端口和地址为“address"、"port"的客户
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				System.out.println("Client port: "+port);
				packet = new DatagramPacket(buf,buf.length,address,port);
				socket.send(packet);
			}catch(IOException e){
				e.printStackTrace();
				hasMoreWork = false;
			}
		}
		socket.close();
	}
}
