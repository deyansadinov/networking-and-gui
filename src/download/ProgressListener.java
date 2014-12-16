package download;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ProgressListener {

  void onProgressWasUpdate(int savedBytes);
}
