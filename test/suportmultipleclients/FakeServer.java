package suportmultipleclients;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class FakeServer {

  private final int port;
  private List<Socket> listClient = new CopyOnWriteArrayList<Socket>();
  private Boolean running = false;
  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter writer;
  private Integer countClients = 1;

  public FakeServer(int port) {
    this.port = port;
  }

  public void startServer(){
    try {
      serverSocket = new ServerSocket(port);
      running = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (running){
            try {
              clientSocket = serverSocket.accept();
              countClients++;

              listClient.add(clientSocket);

              writer = new PrintWriter(clientSocket.getOutputStream());
              writer.println("Hello user " + countClients);
              writer.flush();

              if (listClient.size() > 1){
                synchronized (listClient){
                  for (Socket socket : listClient){
                    writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                    writer.println("User 2 is connected");
                    writer.flush();
                  }
                }
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop(){
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    running = false;
  }

}
