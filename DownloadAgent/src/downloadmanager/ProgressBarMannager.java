package downloadmanager;

import javax.swing.*;

/**
 * Created on 14-11-26.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class ProgressBarMannager implements Runnable {

  JProgressBar progressBar;
  private URLDownloader urlDownloader;
  private final Thread URLtread;

  public ProgressBarMannager(JProgressBar progressBar, URLDownloader urlDownloader, Thread URLtread) {

    this.progressBar = progressBar;
    this.urlDownloader = urlDownloader;
    this.URLtread = URLtread;
  }

  @Override
  public void run() {
    while (URLtread.isAlive()) {
      progressBar.setValue(urlDownloader.getTotalBytesDownloaded());
    }

  }
}
