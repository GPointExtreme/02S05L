package networking.lv.pingpong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
					
					Scanner scanner = new Scanner(System.in);
					scanner.nextLine();
					
					pc.stopServer();
					System.out.println("Server stopped");
					scanner.close();
					
					try {
						t.join();
						break;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
