package chapter10;
import java.io.*;
import java.net.*;
public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = readFileByByte("1.txt");
		System.out.println(content);
		sendContent(content);
		
	}
	//向服务器端发送内容
	public static void sendContent(String content){
		try{
			Socket socket = new Socket("127.0.0.1",6000);
			OutputStream os = socket.getOutputStream();
			os.write(content.getBytes());
			os.close();
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//以字节为单位读取文件内容
	public static String readFileByByte(String fileName){
		File file = new File(fileName);
		String str = null;
		InputStream in = null;
		try{
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in =new FileInputStream(fileName);
			StringBuffer sb = new StringBuffer();
			while((byteread=in.read(tempbytes))!=-1){
				sb.append(new String(tempbytes,0,byteread));
			}
			str = sb.toString();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	

}
