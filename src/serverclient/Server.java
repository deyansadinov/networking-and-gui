package serverclient;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Server {

  private final ServerMessageListener serverMessageListener;
  private ServerMessages serverMessages;
  private final Clock clock;
  public ServerSocket serverSocket;
  private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  public OutputStream outputStream;
  private Boolean started = false;

  public Server(ServerMessageListener serverMessageListener, ServerMessages serverMessages, Clock clock) {
    this.serverMessageListener = serverMessageListener;
    this.serverMessages = serverMessages;
    this.clock = clock;
  }

  public void startServer(int port) {
    started = true;
    try {
      serverSocket = new ServerSocket(port);
      serverMessageListener.newClientWasConnected(serverMessages.startServer());
      new Thread(new Runnable() {

        PrintWriter writer;
        @Override
        public void run() {
          while (started) {
            try {
              Socket clientSocket = serverSocket.accept();

              serverMessageListener.newClientWasConnected(serverMessages.acceptServer());

              outputStream = clientSocket.getOutputStream();

              writer = new PrintWriter(new BufferedOutputStream(outputStream));

              String message = serverMessages.sayHello() + " " + dateFormat.format(clock.date()) + "\n";


              writer.println(message);
              writer.flush();

              serverMessageListener.newClientWasConnected(serverMessages.sendMessage());

            } catch (SocketException se) {

              break;
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          writer.println("\nServer Disconnected\n");
          writer.flush();
        }
      }).start();
    } catch (IOException e) {

    }
  }

  public void stopServer() {
    if (serverSocket != null) {
      started = false;
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      Thread.interrupted();
    }
  }
}
