package networking.lv.pingpong;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ConsoleReader implements Runnable{

	private ServerSocket socket;
	
	public ConsoleReader(ServerSocket socket) {
		super();
		this.socket = socket;
	}

	public boolean Stop;

	@Override
	public void run() {
		Stop = false;
		Scanner sc = new Scanner(System.in);
		while(true) {
			if(sc.nextLine().equals("exit")) {
				System.out.println("stopped via exit");
				Stop = true;
				break;
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
