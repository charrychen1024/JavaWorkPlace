package exercise;
import java.net.*;
import java.io.*;
public class GetDayTime {
	public static void main(String[] args){
		String host = "www.sina.cn";
		try{
			Socket connection = new Socket(host,13);
			System.out.println("Connection established: ");
			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			String daytime = br.readLine();
			System.out.println("Daytime: "+daytime);
			connection.close();
		}catch(UnknownHostException uhe){
			System.out.println("Unknown host: "+host);
		}catch(IOException ioe){
			System.out.println("IOException: "+ioe);
		}
	}

}
