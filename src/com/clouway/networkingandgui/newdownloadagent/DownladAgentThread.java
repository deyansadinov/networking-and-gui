package com.clouway.networkingandgui.newdownloadagent;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:11 Dec 14-12-3
 */
public class DownladAgentThread extends Thread {

  private final DownloadAgent downloadAgent;
  private final String urlName;
  private final String newFileName;


  private boolean isActive = true;

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
