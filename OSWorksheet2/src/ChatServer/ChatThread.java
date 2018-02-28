package ChatServer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import Protocol.SimpleProtocol;

public class ChatThread extends Thread {
	
    private Socket socketForClient; 
    private BufferedReader input;
    private DataOutputStream output;
    private SimpleProtocol protocol;
    private boolean stopped; 
	
	public ChatThread (Socket socketForClient, BufferedReader threadInput, DataOutputStream threadOutput) {
		this.socketForClient = socketForClient;
		this.input = threadInput;
		this.output = threadOutput;
	}
	
	public void run(){
    		try {
    			output.writeBytes("Welcome to my server");
    		} catch (IOException e) {
    			System.out.println("client failed to establish connection with server at socket " + socketForClient);
    			e.printStackTrace();
    		}
        while (!stopped) {
        		processMessage();
        }
    }
	
    public void processMessage() {
        String line = input.readLine();
        if (line != null) {
        		String [] message = protocol.decodeMessage(line);
        		if (message[0] == "sign-in") {
        			signIn();
        		} else if (message[0] == "sign-up") {
        			signUp();
        		} else if (message[0] == "get-message") {
        			getMessage();
        		} else if (message[0] == "send-message") {
        			sendMessage();
        		} else {
        			throw new IllegalArgumentException("Message identifier not recognised");
        		}
        }
    }
	
	

}
