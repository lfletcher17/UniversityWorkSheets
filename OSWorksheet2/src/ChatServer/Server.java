package ChatServer;

/**
 * @author lxf736
 * @version 2018-03-01
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	
	private int port; 
    private String host; 
    private int backlog;
    private ServerSocket serverSocket; 
    private ExecutorService clientThreads; 
    private Map<String, String> users;
    private ArrayList<ChatMessage> messages;

    public Server(String host, int port, int backlog) {
        this.port = port;
        this.host = host;
        this.backlog = backlog;
        this.clientThreads = Executors.newCachedThreadPool();
        this.users = new HashMap<String, String>();
        this.messages = new ArrayList<ChatMessage>();
    }
    
    public void start() throws IOException {
        this.serverSocket = new ServerSocket(this.port, this.backlog, (InetAddress.getByName(this.host)));
        System.out.println(this.serverSocket);
        while(true){
            try {
                Socket socketForClient = this.serverSocket.accept();
                System.out.println(socketForClient);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socketForClient.getInputStream()));
                DataOutputStream toClient = new DataOutputStream(socketForClient.getOutputStream());
                this.clientThreads.execute(new ChatThread(this, socketForClient, fromClient, toClient));
            } catch (IOException e) {
                System.out.println("Socket could not be created!");
            }
        }
    }
    
    public void addUser(String user, String pw) {
        users.put(user, pw);
    }
    
    public boolean checkUser(String user) {
        if (users.containsKey(user)) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<ChatMessage> getMessages (int offset) {
        return messages.subList(offset, messages.size());
    }
    
    public int addMessage(String user, String content){
        String offset = Integer.toString(messages.size());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(date);
        if (messages.add(new ChatMessage(offset, user, time, content))) {
            return Integer.parseInt(offset);
        } else {
            return -1;
        }
    }

    public static void main (String [] args)  {
		Server server = new Server(args[0], Integer.parseInt(args[1]), 20);
		try {
			server.start();
		} catch (IOException e) {
			System.out.println("Server could not be started with provided args! Please ensure args are correct.");
		}
	}
    
}
