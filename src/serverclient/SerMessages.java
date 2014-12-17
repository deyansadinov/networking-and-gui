package serverclient;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class SerMessages implements ServerMessages {
  @Override
  public String startServer() {
    return "Server starting on port 4444 and listener for required.";
  }

  @Override
  public String acceptServer() {
    return "Server accept new client.";
  }

  @Override
  public String sayHello() {
    return "Hello ";
  }

  @Override
  public String sendMessage() {
    return "Server send message to client";
  }
}
