package download;


import java.net.URISyntaxException;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ThreadDownload extends Thread {

  private final DownloadAgent downloadAgent;
  private final String filePath;
  private final String pageAddress;

  public ThreadDownload(DownloadAgent downloadAgent, String filePath, String pageAddress) {
    this.downloadAgent = downloadAgent;
    this.filePath = filePath;
    this.pageAddress = pageAddress;
  }

  public void run(){
    try {
      downloadAgent.downloadStart(filePath,pageAddress);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    if (currentThread().isInterrupted()){
      downloadAgent.isInterrupted(true);
    }
  }
}
