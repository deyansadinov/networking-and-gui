package suportingmultipleclients;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerEntryPoint {
  public static void main(String[] args) {

    ServerView view = new ServerView();

    ServerMessages messages = new ServerMessages() {
      @Override
      public String connectNewClient(int countClients) {
        return "Client " + countClients + " is connected";
      }

      @Override
      public String sendFirstMessageToClient(int countClients) {
        return "Hello client " + countClients;
      }

      @Override
      public String sendMessageToAllClients(int countClients) {
        return "Client " + countClients + " is connected";
      }

      @Override
      public String startServer() {
        return "Server starting on port 3333 and listener for required.";
      }
    };

    Server server = new Server(messages,view);

    view.setServer(server);

    server.start(3333);
  }
}
