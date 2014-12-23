package suportingmultipleclients;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ServerMessageListener {
  void newClientWasConnected(String messages);
}
