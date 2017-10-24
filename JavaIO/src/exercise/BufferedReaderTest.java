package exercise;
import java.io.*;
public class BufferedReaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("3.txt"));
			BufferedReader br = new BufferedReader(new FileReader("3.txt"));
			String s =null;
			for(int i = 1;i<=100;i++){
				s = String.valueOf(Math.random());
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			while((s=br.readLine())!=null){
				System.out.println(s);
			}
			bw.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
