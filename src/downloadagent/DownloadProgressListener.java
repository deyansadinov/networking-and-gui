package downloadagent;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface DownloadProgressListener {

  void onProgressWasUpdate(int savedBytes, long sizeDownloadFile);

}
