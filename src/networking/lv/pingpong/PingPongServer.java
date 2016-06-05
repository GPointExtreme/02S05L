package networking.lv.pingpong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongServer {

	public static void main(String[] args) {
		
		try (
			ServerSocket srv = new ServerSocket(3333);
			){
				while(true) {
					Socket client = srv.accept();
					System.out.println("client connected");
					processClient pc = new processClient(client);
					Thread t = new Thread(pc);
					t.start();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
