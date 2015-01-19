package suportmultipleclients;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class FakeClient {

  private String host;
  private Integer port;
  private Socket socket;
  private Boolean running = false;

  public FakeClient(String host, Integer port) {
    this.host = host;
    this.port = port;
  }

  public void connect() {
    try {
      socket = new Socket(host, port);
      running = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (running) {

          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
      writer.println("disconnecting");
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (writer != null) ;
        writer.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
