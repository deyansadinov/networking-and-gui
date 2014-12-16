package test4;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Main {
  public static void main(String[] args) {
    DownloadAgentUI downloadAgentUI = new DownloadAgentUI();
    DownloadAgent downloadAgent = new DownloadAgent(downloadAgentUI);

    downloadAgentUI.init(downloadAgent);
  }
}
