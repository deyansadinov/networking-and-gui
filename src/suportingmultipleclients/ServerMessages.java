package suportingmultipleclients;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ServerMessages {
  String connectNewClient(int countClients);

  String sendFirstMessageToClient(int countClients);

  String sendMessageToAllClients(int countClients);

  String startServer();
}
