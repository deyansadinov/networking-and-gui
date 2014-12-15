package com.clouway.networkingandgui.downloadagent.agent;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:03 Nov 14-11-29
 */
public class Main {
  public static void main(String[] args) {
    NewDownloadAgentUI newDownloadAgentUI = new NewDownloadAgentUI();
    NewDownloadAgent newDownloadAgent = new NewDownloadAgent(newDownloadAgentUI);
    // download.downloadFile("/home/clouway/Desktop/car.jpg", "aaaa");
    newDownloadAgentUI.init(newDownloadAgent);
  }
}
