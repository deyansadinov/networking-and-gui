package download;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface ProgressListener {

  //updating the progress bar
  void onProgressWasUpdate(int savedBytes);
}
