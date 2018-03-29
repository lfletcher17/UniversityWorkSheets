package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ClientServerMessages.*;
import ClientServerMessages.Message;

/**
 * A ClientThread class which upon Instantiation / calling of the run() method handles all communication for a given
 * @author lxf736
 * @author lxf736
 * @version 2018-03-04
 */

public class ClientThread extends Thread {

    private Server server;
    private Socket socketForClient;
    private ObjectInputStream fromClient;
    private ObjectOutputStream toClient;
    private User user;
    private boolean stopped;

    /**
     * Constructs an Object of ClientThread. Requires the Server that the ClientThread is to run on and a Socket
     * on which to listen to the corresponding Client. Creates an ObjectInputStream and ObjectOutputStream to receive /
     * send Messages across.
     * @param server the Server on which this ClientThread will run
     * @param socketForClient the Socket on which this ClientThread will communicate with the corresponding Client.
     */
    public ClientThread(Server server, Socket socketForClient)  {

        this.server = server;
        this.socketForClient = socketForClient;

        try {
            fromClient = new ObjectInputStream(socketForClient.getInputStream());
            toClient = new ObjectOutputStream(socketForClient.getOutputStream());
        }  catch (IOException e) {
            System.out.println("client failed to establish connection with server at socket " + socketForClient);
            e.printStackTrace();
        }

    }

    /**
     * a run method which will listen for Messages from the corresponding Client. Upon receipt of a Message
     * helper methods are called to determine how the message should be processed.
     */
    public void run(){
        while(true) {
            try {
                if (!stopped) {
                    Message currentMessage = interpretMessage();
                    if (currentMessage != null) {
                        processMessage(currentMessage);
                    }
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Arguments passed by client not recognised");
            } catch (IOException e) {
                System.out.println("Client could not be reached, terminating thread");
                stopThread();
            }
        }
    }

    /**
     * a method which reads Messages from the Client via the ObjectInputStream
     * @return a Message received from the Client
     * @throws IOException
     */
    public Message interpretMessage() throws IOException {
        Message message = null;
        try {
            message = (Message)fromClient.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("The returned UserMessage type was not recognised! Please ensure it has been imported");
        }
        if (message != null) {
            return message;
        } else {
            return null;
        }
    }

    /**
     * Takes a given message and determines it's type. Depending on it's type an appropriate Server method is called to handle.
     * @param message
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public void processMessage(Message message) throws IllegalArgumentException, IOException {
        if (message instanceof SignUpMessage) {
            System.out.println("Server received SignUpMessage from client");
            server.signUp((SignUpMessage)message, this);
        } else if (message instanceof SignInMessage) {
            System.out.println("Server received SignInMessage from client");
            server.signIn((SignInMessage) message, this);
        } else if (message instanceof StartConversationMessage) {
            System.out.println("Server received StartConversationMessage from client");
            server.newConversation((StartConversationMessage) message, this);
        } else if(message instanceof MessagingMessage) {
            System.out.println("Server received MessagingMessage from client");
            server.newMessage((MessagingMessage) message, this);
        } else if(message instanceof ResetPasswordMessage) {
            System.out.println("Server received ResetPasswordMessage from client");
            server.resetPassword((ResetPasswordMessage) message, this);
        } else if(message instanceof ChangePasswordMessage) {
            System.out.println("Server receied ChangePasswordMessage from client");
            server.changePassword((ChangePasswordMessage) message, this);
        } else if(message instanceof UpdateStatusMessage) {
            System.out.println("Received update status message from server");
            server.updateUserStatus((UpdateStatusMessage) message, this);
        } else {
            throw new IllegalArgumentException("Command identifier not recognised");
        }
    }

    /**
     * a method to get this ClientThreads ObjectOutputStream (toClient)
     * @return this ClientThreads ObjectOutputStream (toClient)
     */
    public ObjectOutputStream getToClient() {
        return toClient;
    }

    /**
     * sets the User whose Client this ClientThread is communicating with
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * gets the User whose Client this ClientThread is communicating with
     */
    public User getUser() {
        return this.user;
    }

    /**
     * updates the Clients onlineUserList with a user who has logged in / logged out
     * @param newUser - the user who has logged in / logged off
     * @param login - true if the newUser has logged in, false if they have logged off
     */
    public void updateOnlineUsers(User newUser, boolean login) throws IOException {
        toClient.writeObject(new UpdateOnlineUsersMessage(newUser, login));
    }

    /**
     * informs the Client of a change in an online Users status (i.e. a User has successfully updated their status).
     * @param message - an UpdateStatusMessage to be used by the Client to determine whose status has changed / what it
     * has changed to.
     */
    public void updateUserStatus(UpdateStatusMessage message) throws IOException {
        toClient.writeObject(message);
    }


    /**
     * Stops this instance of ClientThread from running, closes all dependencies (i.e. Socket, Input & OutputStreams etc.
     */
    public void stopThread() {
        this.stopped = true;
        try {
            socketForClient.close();
            System.out.println(socketForClient + " connection closed");
        } catch (IOException e) {
            System.out.println( socketForClient + " socket not Closed!");
        }
        this.interrupt();
        if (this.user != null) {
            this.server.signOutUser(this.user);
        }
        System.out.println(this.getName() + " terminated");
    }

    /**
     * constructs a String which will include this Threads name - used when Sending Messages to Clients.
     * @param thread
     * @return
     */
    public String constructSendingThread(Thread thread) {
        return "Sending via " + thread.getName() + ": ";
    }

}
