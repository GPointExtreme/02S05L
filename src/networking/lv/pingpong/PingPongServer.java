package networking.lv.pingpong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongServer {

	public static void main(String[] args) {
		
		try (
			ServerSocket srv = new ServerSocket(3333);
			){
				Socket client = new Socket();
				while(true) {
					client = srv.accept();
					processClient(client);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void processClient(Socket client) {
		try (
				InputStreamReader isr = new InputStreamReader(client.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
				BufferedWriter bw = new BufferedWriter(osw);
				) {
				String line;
				while((line = br.readLine()) != null) {
					if(line.equals("ping")) {
						bw.write("pong" + "\n");
					}
					else if(line.equals("pong")) {
						bw.write("ping" + "\n");
					}
					else {
						bw.write("error" + "\n");
					}
					bw.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
