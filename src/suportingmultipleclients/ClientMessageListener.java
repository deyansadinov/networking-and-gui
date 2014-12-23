package suportingmultipleclients;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ClientMessageListener {
  void onResponseWasReceived(String message);
}
