package downloadmanager;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created on 14-11-25.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class URLDownloader implements Runnable {

  private final String imageUrl;
  private final String destinationFile;
  private final JProgressBar progressBar;
  private final DownloadProgressWindow downloadProgressWindow;

  private Integer totalBytesDownloaded = 0;
  private int fileLenth;
  private InputStream is;
  private OutputStream os;


  public URLDownloader(String imageUrl, String destinationFile, JProgressBar progressBar, DownloadProgressWindow downloadProgressWindow) {

    this.imageUrl = imageUrl;
    this.destinationFile = destinationFile;
    this.progressBar = progressBar;
    this.downloadProgressWindow = downloadProgressWindow;
  }

  @Override
  public void run() {

    File file = new File(destinationFile);

    if (!file.exists()) {
      try {

        setupURLConnection();
        downloadTheFile(file);

      } catch (IOException e) {

        JOptionPane.showMessageDialog(null, "UNEXPECTED ERROR");
      }
      downloadProgressWindow.dispose();
    } else {

      JOptionPane.showMessageDialog(null, "NAME EXIST or WRONG");
      downloadProgressWindow.dispose();

    }
  }

  private void setupURLConnection() throws IOException {
    URL url = new URL(imageUrl);
    is = url.openStream();

    URLConnection connection = url.openConnection();
    connection.connect();
    fileLenth = connection.getContentLength();

    os = new FileOutputStream(destinationFile);
  }

  private void downloadTheFile(File file) throws IOException {
    progressBar.setMaximum(fileLenth);

    byte[] buffer = new byte[1024];

    int readedBytes;

    while ((readedBytes = is.read(buffer)) != -1 && downloadProgressWindow.canContinueToDownload()) {

      try {
        Thread.sleep(400);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      os.write(buffer, 0, readedBytes);

      totalBytesDownloaded = totalBytesDownloaded + readedBytes;
    }

    if (totalBytesDownloaded != fileLenth) {
      file.delete();
    }

    is.close();
    os.close();
  }

  public Integer getTotalBytesDownloaded() {
    return totalBytesDownloaded;
  }


}
