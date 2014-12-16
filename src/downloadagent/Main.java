package downloadagent;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Main {
  public static void main(String[] args) {
    DownloadAgent downloadAgent = new DownloadAgent();
    DownloadProgressListener downloadProgressListener = new DownloadUIListener(downloadAgent);
    downloadAgent.downloadProgressListener(downloadProgressListener);
//    DownloadAgentUI downloadAgentUI = new DownloadAgentUI(downloadProgressListener) ;
  }
}
