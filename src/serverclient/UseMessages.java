package serverclient;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class UseMessages implements UserMessages {
  @Override
  public String connectClient() {
    return "Client connected to Server";
  }

  @Override
  public String readFromServer() {
    return null;
  }

  @Override
  public String printMessage() {
    return null;
  }

  @Override
  public String closeClient() {
    return null;
  }
}
