package clientsever;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class FakeServer {

  private final int port;
  private final String message;
  private ServerSocket serverSocket;

  public FakeServer(int port , String message) {
    this.port = port;
    this.message = message;
  }

  public void startServer(){
    try{
      serverSocket = new ServerSocket(port);
      while (true){
        Socket clientSocket = serverSocket.accept();

        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write(message.getBytes());
        outputStream.flush();

        clientSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop(){
    if (serverSocket != null){
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
