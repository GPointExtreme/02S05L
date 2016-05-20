package networking.lv.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		try (
			ServerSocket server = new ServerSocket(2222);
			) {
			Socket client = server.accept();
			
			try (
					InputStreamReader isr = new InputStreamReader(client.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					) {
						int i = 0;
						while(i < 3) {
							bw.write(br.readLine());
							bw.newLine();
							bw.flush();
							i++;
						}
						
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
