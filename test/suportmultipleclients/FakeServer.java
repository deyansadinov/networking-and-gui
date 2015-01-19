package suportmultipleclients;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

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
  private Integer countClients = 0;
  private BlockingQueue<Boolean> queue = new ArrayBlockingQueue<Boolean>(1);


  public FakeServer(int port) {
    this.port = port;
  }

  public void startServer() {
    try {
      serverSocket = new ServerSocket(port);
      running = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (running) {
            try {

              try {
                queue.put(true);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              clientSocket = serverSocket.accept();
              count();

              listClient.add(clientSocket);

              writer = new PrintWriter(clientSocket.getOutputStream());
              writer.println("Hello user " + countClients);
              writer.flush();

//              if (listClient.size() > 1){
//                synchronized (listClient){
//                  for (Socket socket : listClient){
//                    writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
//                    writer.println("User 2 is connected");
//                    writer.flush();
//                  }
//                }
//              }
              try {
                queue.take();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            } catch (IOException e) {

            }
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop() {
//    try {
//      Thread.sleep(100);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    running = false;
//
//    try {
//      serverSocket.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    for (Socket x : listClient) {
      try {
        writer = new PrintWriter(new BufferedOutputStream(x.getOutputStream()));
        writer.println("Server is disconnected");
        writer.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {

      if (queue.size() != 0) {
        try {
          queue.take();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      serverSocket.close();
    }catch (IOException e){}
    running = false;
  }

  private synchronized void count() {
    countClients++;
  }

}
