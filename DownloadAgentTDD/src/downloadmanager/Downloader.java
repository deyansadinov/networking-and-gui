package downloadmanager;

/**
 * Created on 14-12-5.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public interface Downloader {

  int processDownloading(String URLAddress, String fileName);

}
