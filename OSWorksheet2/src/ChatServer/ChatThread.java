package ChatServer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import Protocol.SimpleProtocol;

public class ChatThread extends Thread {
	
	private Server server;
    private Socket socketForClient; 
    private BufferedReader fromClient;
    private DataOutputStream toClient;
    private SimpleProtocol protocol;
    private String user;
    private boolean stopped; 
	
	public ChatThread (Server server, Socket socketForClient, BufferedReader fromClient, DataOutputStream toClient)  {
		
		this.server = server;
		this.socketForClient = socketForClient;
		this.fromClient = fromClient;
		this.toClient = toClient;
		this.protocol = new SimpleProtocol();
		
		try {
			toClient.writeBytes(protocol.createMessage("welcome", "Welcome to my server") + "\n");
		}  catch (IOException e) {
			System.out.println("client failed to establish connection with server at socket " + socketForClient);
			e.printStackTrace();
		}
	}
    
    private void signUp(String user, String pw) throws IOException {
		if (server.checkUser(user)) {
			toClient.writeBytes(protocol.createMessage("sign-up", "false", "user already exists, please try signing in") + "\n");
		} else if (user.length() < 5 || user.length() > 20) {
			toClient.writeBytes(protocol.createMessage("sign-up", "false", "username must be between 5 and 20 characters in length") + "\n");
		} else if (pw.length() < 8 || pw.length() >32) {
			toClient.writeBytes(protocol.createMessage("sign-up", "false", "password must be between 8 and 32 characters in length") + "\n");
		} else {
			server.addUser(user, pw);
			toClient.writeBytes(protocol.createMessage("sign-up", "true", "sign up successful, please sign in with these credentials") + "\n");
			stopThread();
		}
}

	private void signIn(String user, String pw) throws IOException {
	    this.user = user;
	    if (server.checkUser(user)) {
	        toClient.writeBytes(protocol.createMessage("sign-in", "true", "welcome back, user") + "\n");
	    } else {
	    		toClient.writeBytes(protocol.createMessage("sign-in", "false", "password/username does not match") + "\n");
	    }
	}
    
	
	public void run(){
		while(true)
			try {
				if (!stopped) {
		    			String currentMessage[] = interpretMessage();
		    			if (currentMessage.length > 0) {
		    				processMessage(currentMessage);
		    			}
				} else {
					break;
				}
	    } catch (IllegalArgumentException | IOException e) {
	    		//TO DO
	    }
	}
	
	public String [] interpretMessage() throws IOException {
		String clientCommand = fromClient.readLine();
        if (clientCommand != null) {
        		System.out.println(protocol.decodeMessage(clientCommand));
            return protocol.decodeMessage(clientCommand);
        } else {
        		return new String [0];
        }
		
	}
	
    public void processMessage(String [] message) throws IllegalArgumentException, IOException {
        if (message.length != 0) {
        		if (message[0].equals("sign-in")) {
        			signIn(message[1], message[2]);
        		} else if (message[0].equals("sign-up")) {
        			signUp(message[1], message[2]);
        		} //TO DO - SECTION TO HANDLE ACTUAL MESSAGES!!
        		/*else if (message[0].equals("get-message")) {
        			getMessage();
        		} else if (message[0].equals("send-message")) {
        			sendMessage();
        		} */else {
        			throw new IllegalArgumentException("Command identifier not recognised");
        		}
        }
    }
    
   
    
    private void stopThread() {
        this.stopped = true;
        try {
			socketForClient.close();
			System.out.println(socketForClient + " connection closed");
		} catch (IOException e) {
			System.out.println( socketForClient + " socket not Closed!");
		}
        this.interrupt();
        System.out.println(this.getName() + " terminated");
    }
    
    

	
	

}


