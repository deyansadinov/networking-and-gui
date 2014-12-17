package serverclient;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Server {

  private final ServerMessageListener serverMessageListener;
  private ServerMessages serverMessages;
  private final Clock clock;
  private int port;
  public ServerSocket serverSocket;
  private static final DateFormat dateFormat = new SimpleDateFormat("yy-dd");
  public OutputStream outputStream;
  private PrintWriter writer;
  private Boolean started = false;

  public Server(ServerMessageListener serverMessageListener, ServerMessages serverMessages, Clock clock) {
    this.serverMessageListener = serverMessageListener;
    this.serverMessages = serverMessages;
    this.clock = clock;
  }

  public void startServer(int port) {
    this.port = port;
    started = true;
    try {
      serverSocket = new ServerSocket(port);
      serverMessageListener.newClientWasConnected(serverMessages.startServer());
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (started) {
            try {
              Socket clientSocket = serverSocket.accept();

              serverMessageListener.newClientWasConnected(serverMessages.acceptServer());

              outputStream = clientSocket.getOutputStream();

              writer = new PrintWriter(new BufferedOutputStream(outputStream));

              String message = serverMessages.sayHello() + " " + dateFormat.format(clock.now()) + "\n";
              System.out.println("message is : " + message);

              writer.println(message);
              writer.flush();

              serverMessageListener.newClientWasConnected(serverMessages.sendMessage());

            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    } catch (IOException e) {
    }
  }

  public void stopServer() {
    if (serverSocket != null) {
      writer.println("\nServer Disconnected\n");
      writer.flush();
      started = false;
      Thread.interrupted();
    }
  }
}
