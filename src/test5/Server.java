package test5;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Server {

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  private final ServerMessageListener serverMessageListener;
  private final ServerMessages serverMessages;
  private final Clock clock;
  private final int port;
  private ServerSocket serverSocket;
  private OutputStream outputStream;

  public Server(ServerMessageListener serverMessageListener, ServerMessages serverMessages, Clock clock, int port) {
    this.serverMessageListener = serverMessageListener;
    this.serverMessages = serverMessages;
    this.clock = clock;
    this.port = port;
  }

  public void startServer() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          serverSocket = new ServerSocket(port);
          serverMessageListener.newClientWasConnected(serverMessages.startServer());

          while (true) {
            Socket clientSocket = serverSocket.accept();
            serverMessageListener.newClientWasConnected(serverMessages.acceptClient());

             outputStream = clientSocket.getOutputStream();
            outputStream.write((serverMessages.gatHello() + DATE_FORMAT.format(clock.now())).getBytes());
            outputStream.flush();

            serverMessageListener.newClientWasConnected(serverMessages.sendMessage());

            clientSocket.close();
          }
        } catch (SocketException e) {

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public void stopServer() {
    if (serverSocket != null) {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void stiopMessage() {
    String message = "stop";
    try {
      outputStream.write((message).getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
