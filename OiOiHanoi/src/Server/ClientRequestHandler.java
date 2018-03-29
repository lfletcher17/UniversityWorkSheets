package Server;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * A ClientRequestHandler class which listens for incoming Client Connections on a given ServerSocket.
 * ClientRequestHandler is created and started by the Server when booting. The ClientRequestHandler runs on it's
 * own designated Thread. When a Client connection is accepted, the ClientRequestHandler creates a new ClientThread within the
 * Servers clientThreadPool. The ClientThread is given a socket to communicate with the corresponding Client via.
 * @author lxf736
 * @version 2018-03-10
 */

public class ClientRequestHandler extends Thread {

  private Server server;
  private ExecutorService threadPool;
  private ServerSocket serverSocket;
  private boolean interrupted;

  /**
   * Constructs an instance of ClientRequestHandler. Taking the Server who created it, the Servers threadPool and serverSocket
   * as params.
   * @param server - the Server who created this ClientRequestHandler
   * @param threadPool - the Server's threadPool
   * @param serverSocket - the Server's serverSocket
   */
  public ClientRequestHandler(Server server, ExecutorService threadPool,
      ServerSocket serverSocket) {
    this.server = server;
    this.threadPool = threadPool;
    this.serverSocket = serverSocket;
    this.interrupted = false;
  }

  /**
   * a run() method which listens for incoming Client connection requests. Upon successful receipt of a Client connection
   * request a socket is created for the Client and a ClientThread is constructed to handle all future communication with the Client.
   */
  public void run() {
    while (!interrupted) {
      System.out.println("waiting for client");
      Socket socketForClient = null;
      try {
        socketForClient = this.serverSocket.accept();
        threadPool.execute(new ClientThread(this.server, socketForClient));
      } catch (IOException e) {
        interrupted = true;
      }
    }
  }

}
