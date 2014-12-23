package suportingmultipleclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Client {

  private final UserMessages userMessages;
  private final ClientMessageListener clientMessageListener;
  private boolean running = false;
  private StringBuilder builder;


  public Client(UserMessages userMessages, ClientMessageListener clientMessageListener) {
    this.userMessages = userMessages;
    this.clientMessageListener = clientMessageListener;
  }

  public void connect(String host, int port) {
    try {
      running = true;
      final Socket clientSocket = new Socket(host, port);
      clientMessageListener.onResponseWasReceived(userMessages.connectClient());
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (running) {
            try {
              InputStream inputStream = clientSocket.getInputStream();
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
              clientMessageListener.onResponseWasReceived(reader.readLine());
              if (reader.readLine() == null) {
                running = false;
                inputStream.close();
                reader.close();
                clientSocket.close();
                return;
              }
              builder = new StringBuilder();
              String line = reader.readLine();
              builder.append(line);
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

  public String getResponse() {
    return builder.toString();
  }
}
