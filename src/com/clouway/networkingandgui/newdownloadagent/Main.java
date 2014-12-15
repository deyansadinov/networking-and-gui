package com.clouway.networkingandgui.newdownloadagent;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:36 Dec 14-12-2
 */
public class Main {
  public static void main(String[] args) {
    DownloadAgentUI downloadAgentUI = new DownloadAgentUI();
    DownloadAgent downloadAgent = new DownloadAgent(downloadAgentUI);

    downloadAgentUI.init(downloadAgent);
  }
}
