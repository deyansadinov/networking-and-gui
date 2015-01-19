package clientsever;

import serverclient.Clock;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class FakeServer {

  private final int port;
//  private final String message;
  private ServerSocket serverSocket;
  private Clock clock;
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public FakeServer(int port,Clock clock) {
    this.port = port;

    this.clock = clock;
  }

  public void startServer() {
    new Thread(new Runnable() {
      @Override
      public void run() {
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
            out.println("Hello " + dateFormat.format(clock.date()));
//            out.println(message);
            out.flush();
          } catch (SocketException se) {
            break;
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();


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
