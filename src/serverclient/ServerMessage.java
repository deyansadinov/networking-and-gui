package serverclient;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ServerMessage {

  String startServer();

  String acceptServer();

  String sayHello();

  String sendMessage();
}
