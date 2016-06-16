package networking.lv.pingpong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PingPongServer {

	public static void main(String[] args) {
		
		ArrayList<processClient> list = new ArrayList<>();
		try (
			ServerSocket srv = new ServerSocket(3333);
			){
					ConsoleReader cr = new ConsoleReader(srv);
					Thread tConsole = new Thread(cr);
					tConsole.start();
					
					while(cr.Stop != true) {
						Socket client = srv.accept();
						System.out.println("client connected");
						processClient pc = new processClient(client);
						list.add(pc);
						
						Thread t = new Thread(pc);
						t.start();
					}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (processClient processClient : list) {
			processClient.stopClient();
		}

	}

}
