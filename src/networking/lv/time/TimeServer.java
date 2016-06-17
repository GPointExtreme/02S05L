package networking.lv.time;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeServer {

	public static void main(String[] args) {
		try (
			ServerSocket server = new ServerSocket(1111);
			) {
			for (int i = 1; i <= 3; i++) {
			Socket client = server.accept();

				try (
					OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					) {
						bw.write(LocalDateTime.now().toString()+" "+i+"\n");
						bw.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
