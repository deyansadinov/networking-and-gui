package serverclient;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientMain {

  public static void main(String[] args) {
    ClientView clientView = new ClientView();

    UserMessage message = new UseMessage();

    Client client = new Client(clientView,message,4444);

    client.connect("localhost");

  }
}