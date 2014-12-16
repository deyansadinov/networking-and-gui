package serverclient;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ClientMessageListener {

  void onNewMessageReceive(String message);
}
