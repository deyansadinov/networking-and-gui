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
  private final ServerMessages serverMessages;
  private final int countClients;



  public ClientSocketThread(List<Socket> listClients, ServerMessages serverMessages, int countClients) {
    this.listClients = listClients;
    this.serverMessages = serverMessages;
    this.countClients = countClients;
  }

  @Override
  public void run() {
    if (listClients.size() > 1) {
      for (Socket socket : listClients) {
        try {
          PrintWriter writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
          writer.println(serverMessages.sendMessageToAllClients(countClients));
          writer.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }


  }
}
