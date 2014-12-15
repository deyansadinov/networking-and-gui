package com.clouway.networkingandgui.downloadagent.agent;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:59 Nov 14-11-29
 */
public class DownloadThread extends Thread {

  private NewDownloadAgent newDownloadAgent;
  private final String urlName;
  private final String fileName;

  public DownloadThread(NewDownloadAgent newDownloadAgent, String urlName, String fileName) {
    this.newDownloadAgent = newDownloadAgent;
    this.urlName = urlName;
    this.fileName = fileName;
  }

  public void run() {

    newDownloadAgent.downloadFile(urlName, fileName);

  }
}
