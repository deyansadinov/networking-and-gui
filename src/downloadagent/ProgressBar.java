package downloadagent;

import java.io.File;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ProgressBar extends Thread {

  private final DownloadAgentUI downloadAgentUI;
  private final int sizeDownloadPage;
  private final String filePath;
  private final File file;
  private int currentSizeOfFile = 0;

  public ProgressBar(DownloadAgentUI downloadAgentUI,int sizeDownloadPage,String filePath) {
    this.downloadAgentUI = downloadAgentUI;
    this.sizeDownloadPage = sizeDownloadPage;
    this.filePath = filePath;
    this.file = new File(filePath);
    downloadAgentUI.getProgressBar().setMaximum(sizeDownloadPage);
  }

  public void run(){
    downloadAgentUI.getProgressBar().setValue(0);
    while ((currentSizeOfFile = (int) file.length()) <= sizeDownloadPage){
      downloadAgentUI.getProgressBar().setValue(currentSizeOfFile + 1);
    }
  }
}
