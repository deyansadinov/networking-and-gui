package clientsever;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class FakeServer {

  private final int port;
  private final String message;
  private ServerSocket serverSocket;

  public FakeServer(int port, String message) {
    this.port = port;
    this.message = message;
  }

  public void startServer() {

    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (true) {
      Socket clientSocket ;
      try {
        clientSocket = serverSocket.accept();
        OutputStream outputStream = clientSocket.getOutputStream();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(outputStream));
        out.println(message);
        out.flush();
      } catch (SocketException se) {
        break;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public void stop() {
    if (serverSocket != null) {
      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
