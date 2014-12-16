package test5;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class UsMessages implements UserMessages {

  @Override
  public String connectClient() {
    return "safsdfsdf";
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
