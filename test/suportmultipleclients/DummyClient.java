package suportmultipleclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DummyClient {

  private StringBuilder response = new StringBuilder();
  private int port;
  private ArrayBlockingQueue<Boolean> waitQueue = new ArrayBlockingQueue<Boolean>(1);

  public DummyClient(int port) {
    this.port = port;
  }

  public void connect() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Socket socket = null;
        try{
        while (true) {
          try {
            socket = new Socket("localhost", port);
            break;

          } catch (ConnectException e) {
            e.printStackTrace();
          }
        }
          System.out.println("after while");
          InputStream inputStream = socket.getInputStream();
          Scanner scanner = new Scanner(inputStream);


          while (scanner.hasNext()){
            response.append(scanner.nextLine());
            waitQueue.add(true);
          }
        }catch (IOException e){
          e.printStackTrace();
        }finally {
          if (socket != null){
            try {
              socket.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }).start();
  }

  public String getResponse(){
    try {
      waitQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return response.toString();
  }
}
