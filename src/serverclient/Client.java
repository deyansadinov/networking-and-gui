package serverclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Client {

  private final ClientMessageListener clientMessageListener;
  private final UserMessage userMessage;
  private final int port;
  private StringBuilder builder = new StringBuilder();
  private Socket clientSocket;
  private PrintWriter writer;


  public Client(ClientMessageListener clientMessageListener, UserMessage userMessage, int port) {
    this.clientMessageListener = clientMessageListener;
    this.userMessage = userMessage;
    this.port = port;
  }

  public void connect(String host) {
    try {
      clientSocket = new Socket(host, port);
      new Thread(new Runnable() {
        @Override
        public void run() {

          try {
            clientMessageListener.onNewMessageReceive(userMessage.connectClient());

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

  public String getResponse() {
    return builder.toString();
  }

  public void disconnect() {
//    if (clientSocket != null){
    try {
      writer = new PrintWriter(String.valueOf(new BufferedInputStream(clientSocket.getInputStream())));
      writer.println("\nClient Disconnected\n");
      writer.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
//    }
  }
}
