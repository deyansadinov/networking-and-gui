package chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by clouway on 14-12-10.
 */
public class LearnToUseSocketsTest {

  interface ConnectionListener<T> {
    void onClientConnect(T client);
  }

  class SocketConnectionLister implements ConnectionListener<Socket> {
    @Override
    public void onClientConnect(final Socket client) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            try {
              PrintWriter writer = new PrintWriter(client.getOutputStream());
              writer.println("Ping! " + new Date());
              writer.flush();
              Thread.sleep(1000);
            } catch (IOException e) {
              e.printStackTrace();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    }
  }


  class Server {

    private  Boolean isStarted = false;
    private Socket client;
    private final List<ConnectionListener<Socket>> listeners;


    public Server(List<ConnectionListener<Socket>> listeners) {
      this.listeners = listeners;
    }

    protected void start(Integer port) {
      isStarted = true;
      try {
        final ServerSocket server = new ServerSocket(port);
        new Thread(new Runnable() {
          @Override
          public void run() {
            while (isStarted) {
              try {
                System.out.println("Accepting...");
                client = server.accept();
                System.out.println("Connected!");
                for (ConnectionListener<Socket> singleListener : listeners) {
                  singleListener.onClientConnect(client);
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

    protected void stop() throws IOException {
      PrintWriter writer = new PrintWriter(client.getOutputStream());
      writer.println("Server shut down!");
      writer.flush();

      isStarted = false;
    }

  }

  class Client {

    protected void connect(String address, Integer port) {
      try {

        final Socket socket = new Socket(address, port);
        new Thread(new Runnable() {
          @Override
          public void run() {
            while (socket.isConnected()) {
              try {
                InputStream stream = socket.getInputStream();
                Scanner scanner = new Scanner(stream);
                while (scanner.hasNext()) {
                  System.out.println(scanner.nextLine());
                }

                Thread.sleep(1000);
              } catch (IOException e) {
                e.printStackTrace();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            System.out.println("Server disconnected!");
          }
        }).start();


      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  private Server server;

  @Before
  public void setUp() throws Exception {
    server = new Server(new ArrayList<ConnectionListener<Socket>>() {{
      this.add(new SocketConnectionLister());
    }});
    server.start(8080);
  }

  @After
  public void tearDown() throws Exception {
    server.stop();
  }

  @Test
  public void happyPath() throws Exception {
    Client client = new Client();
    client.connect("127.0.0.1", 8080);
    Thread.sleep(6000);
    System.out.println("Stopping server...");
    server.stop();

  }
}