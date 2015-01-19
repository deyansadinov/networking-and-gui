package suportingmultipleclients;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientSocketThread extends Thread {
  private final List<Socket> listClients;
  private final Socket clientSocket;
  private final ServerMessages serverMessages;
  private final int countClients;
  private PrintWriter writer;


  public ClientSocketThread(List<Socket> listClients, Socket clientSocket, ServerMessages serverMessages, int countClients) {
    this.listClients = listClients;
    this.clientSocket = clientSocket;
    this.serverMessages = serverMessages;
    this.countClients = countClients;
  }

  @Override
  public void run() {
    try {
      writer = new PrintWriter(clientSocket.getOutputStream(), true);
      writer.println(serverMessages.sendFirstMessageToClient(countClients));

      new Thread(new Runnable() {
        @Override
        public void run() {
          for (Socket client : listClients) {
            try {
              writer = new PrintWriter(client.getOutputStream(), true);
            } catch (IOException e) {
              e.printStackTrace();
            }
            writer.println(serverMessages.sendMessageToAllClients(countClients));
            writer.flush();
          }
          listClients.add(clientSocket);
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
