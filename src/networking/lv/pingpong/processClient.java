package networking.lv.pingpong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class processClient implements Runnable{
	
	private Socket client;
	private boolean stop;
	private Object lock = new Object();

	public processClient(Socket client) {
		super();
		this.client = client;
	}
	
	public void stopClient() {
		this.stop = true;
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		synchronized(lock) {
				try (
					InputStreamReader isr = new InputStreamReader(client.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					) {
						String line;
						while(stop != true && (line = br.readLine()) != null) {
							if(line.equals("ping")) {
								bw.write("pong" + "\n");
							}
							else if(line.equals("pong")) {
								bw.write("ping" + "\n");
							}
							else if(line.equals("exit")) {
								bw.write("connection closed" + "\n");
								break;
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
}
