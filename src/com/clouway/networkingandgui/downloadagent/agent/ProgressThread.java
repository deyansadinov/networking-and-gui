package com.clouway.networkingandgui.downloadagent.agent;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:04 Nov 14-11-29
 */
public class ProgressThread extends Thread {
  private NewDownloadAgent newDownloadAgent;

  public ProgressThread(NewDownloadAgent newDownloadAgent) {
    this.newDownloadAgent = newDownloadAgent;
  }

  public void run() {

    while (true) {
      newDownloadAgent.getTransferred();

    }
  }
}
