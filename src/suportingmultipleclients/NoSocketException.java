package suportingmultipleclients;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class NoSocketException extends RuntimeException {

  public NoSocketException() {
    System.err.println("Server closed");
  }
}
