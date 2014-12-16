package serverclient;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface UserMessage {

  String connectClient();

  String readFromServer();

  String printMessage();

  String closeClient();
}
