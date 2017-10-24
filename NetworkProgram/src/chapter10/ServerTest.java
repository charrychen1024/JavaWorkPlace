package chapter10;
import java.io.*;
import java.net.*;
public class ServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		newFile("2.txt",receiveContent());

	}
	public static String receiveContent(){
		String str = null;
		try{
			ServerSocket server = new ServerSocket(6000);
			Socket socket = server.accept();
			InputStream is = socket.getInputStream();
			byte[] buf = new byte[100];
			int len = 0;
			StringBuffer sb = new StringBuffer();
			while((len = is.read(buf))!=-1){
				sb.append(new String(buf,0,len));
			}
			str = sb.toString();
			is.close();
			socket.close();
			server.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	//将接收到的内容写入文件
	public static void newFile(String fileName,String fileContent){
		try{
			String filePath = fileName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if(!myFilePath.exists()){
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
			System.out.println("Sccussed");

		  }catch(Exception e){
			  e.printStackTrace();
		  }
	}

}
