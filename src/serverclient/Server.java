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
  private ServerMessage serverMessage;
  private final Clock clock;
  private final int port;
  public ServerSocket serverSocket;
  private static final DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
  public Socket clientSocket;
  public OutputStream outputStream;
  private PrintWriter writer;
  private Boolean isStarted = false;

  public  Server(ServerMessageListener serverMessageListener, ServerMessage serverMessage, Clock clock, int port) {
    this.serverMessageListener = serverMessageListener;
    this.serverMessage = serverMessage;
    this.clock = clock;
    this.port = port;
  }



  public void startServer() {
    isStarted = true;
    try {
      serverSocket = new ServerSocket(port);
      serverMessageListener.newClientWasConnected(serverMessage.startServer());
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (isStarted) {
            try {
              clientSocket = serverSocket.accept();

              serverMessageListener.newClientWasConnected(serverMessage.acceptServer());

              outputStream = clientSocket.getOutputStream();

              writer = new PrintWriter(new BufferedOutputStream(outputStream));

              String message = serverMessage.sayHello() + dateFormat.format(clock.now()) + "\n";
              System.out.println("message is : " + message);

              writer.println(message);
              writer.flush();

              serverMessageListener.newClientWasConnected(serverMessage.sendMessage());
              clientSocket.close();
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
      isStarted = false;
      Thread.interrupted();
    }
  }
}
