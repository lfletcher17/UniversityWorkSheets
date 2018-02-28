package ChatServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	
	public static void main (String [] args) throws IOException {
		Server server = new Server(args[0], Integer.parseInt(args[1]), 20);
		server.start();
	}
	
	private int port; 
    private String host; 
    private int backlog;
    private ServerSocket serverSocket; 
    private ExecutorService threadPool; 

    public Server(String host, int port, int backlog) {
        this.port = port;
        this.host = host;
        this.backlog = backlog;
        try {
			this.serverSocket = new ServerSocket(this.port, this.backlog, (InetAddress.getByName(this.host)));
		} catch (IOException e) {
			System.out.println("Server was unable to start - ServerSocket could not be established using port: "
		+ port + " at host: " + host);
			e.printStackTrace();
		}
        this.threadPool = Executors.newCachedThreadPool();
    }
    
    
    public void start() {
        while(true){
        		try {
        			//open new socket at server for requesting client
	        		Socket socketForClient = this.serverSocket.accept();
	        		BufferedReader threadInput = new BufferedReader(new InputStreamReader(socketForClient.getInputStream()));
	    			DataOutputStream threadOutput = new DataOutputStream(socketForClient.getOutputStream());
	    			ChatThread thread = new ChatThread(socketForClient, threadInput, threadOutput);
	        		threadPool.execute(thread);
        		} catch (IOException e) {
        			//continue looping in search for new client connections
        		}
        }
    }
    
}
