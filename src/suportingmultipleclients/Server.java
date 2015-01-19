package suportingmultipleclients;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Server {

  private final ServerMessages serverMessages;
  private final ServerMessageListener serverMessageListener;
  private Socket clientSocket;
  private ServerSocket serverSocket;
  private int countClients = 1;
  private List<Socket> listClients = new CopyOnWriteArrayList<Socket>();
  private PrintWriter writer;
  private Boolean running = false;


  public Server(ServerMessages serverMessages, ServerMessageListener serverMessageListener) {
    this.serverMessages = serverMessages;
    this.serverMessageListener = serverMessageListener;
  }

  public void start(int port) {
    try {
      serverSocket = new ServerSocket(port);
//      serverMessageListener.newClientWasConnected(serverMessages.startServer());
      running = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          System.out.println("In thread");
          while (running) {
            try {
              clientSocket = serverSocket.accept();
              serverMessageListener.newClientWasConnected(serverMessages.connectNewClient(countClients));
//
//              OutputStream outputStream = clientSocket.getOutputStream();
//
//              writer = new PrintWriter(new BufferedOutputStream(outputStream));
//
//              writer.println(serverMessages.sendFirstMessageToClient(countClients));
//
//              writer.flush();



              ClientSocketThread clientSocketThread = new ClientSocketThread(listClients, clientSocket, serverMessages, countClients);
              clientSocketThread.start();
              try {
                Thread.sleep(200);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              countClients();

//              for (Socket socket : listClients) {
//                writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
//                writer.println(serverMessages.sendFirstMessageToClient(countClients));
//                writer.println(serverMessages.sendMessageToAllClients(countClients));
//                writer.flush();
//              }


            } catch (IOException e) {
            }
          }
        }
      }).start();
    } catch (IOException e) {
      try {
        running = false;
        serverSocket.close();
        throw new NoSocketException();
      } catch (IOException e1) {
        e1.printStackTrace();
      } finally {
        if (clientSocket != null) {
          try {
            clientSocket.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      }
    }
  }

  private synchronized void countClients() {
    countClients++;
  }


  public void stop() {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    running = false;
    for (Socket socket : listClients) {
      try {
        writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
        writer.println("Server Disconnect");
        writer.flush();
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}



