package serverclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Client {

  private final ClientMessageListener clientMessageListener;
  private final UserMessages userMessages;
  private StringBuilder builder = new StringBuilder();
  private Socket clientSocket;



  public Client(ClientMessageListener clientMessageListener, UserMessages userMessages) {
    this.clientMessageListener = clientMessageListener;
    this.userMessages = userMessages;

  }

  public void connect(String host, int port) {
    try {
      clientSocket = new Socket(host, port);
      new Thread(new Runnable() {
        @Override
        public void run() {

          try {
            clientMessageListener.onNewMessageReceive(userMessages.connectClient());

            InputStream stream = clientSocket.getInputStream();

            Scanner scanner = new Scanner(stream);

            while (scanner.hasNext()) {
              String line = scanner.nextLine();

              System.out.println(line);
              clientMessageListener.onNewMessageReceive(line);


              builder.append(line);

              if (line == null) {
                clientSocket.close();

                return;
              }
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getResponse(){
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return builder.toString();
  }

}
