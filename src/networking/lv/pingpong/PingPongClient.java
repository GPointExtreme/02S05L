package networking.lv.pingpong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PingPongClient {

	public static void main(String[] args) {
		try (
			Socket client = new Socket("127.0.0.1", 3333);
			){
				try (
					OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					InputStreamReader isr = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(isr);
					InputStreamReader isr2 = new InputStreamReader(client.getInputStream());
					BufferedReader br2 = new BufferedReader(isr2);
				) {
					String line;
					while((line = br.readLine()) != null) {
						if(line.equals("exit")) {
							break;
						}
						else {
							bw.write(line);
							bw.newLine();
							bw.flush();
							System.out.println(br2.readLine());
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
