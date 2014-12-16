package test4;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownladAgentThread extends Thread {

  private final DownloadAgent downloadAgent;
  private final String urlName;
  private final String newFileName;




  public DownladAgentThread(DownloadAgent downloadAgent, String urlName, String newFileName) {

    this.downloadAgent = downloadAgent;
    this.urlName = urlName;
    this.newFileName = newFileName;

  }

  public synchronized void run() {

    downloadAgent.startDownload(urlName, newFileName);
    if (currentThread().isInterrupted()) {
      downloadAgent.setStop(true);
    }

  }
}
