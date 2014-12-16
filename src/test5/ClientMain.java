package test5;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientMain {

  public static void main(String[] args) {
    ClientView clientView = new ClientView();

    UserMessages message = new UsMessages();

    Client client = new Client(clientView, message, 4444);
    client.connectToServer();
  }
}
