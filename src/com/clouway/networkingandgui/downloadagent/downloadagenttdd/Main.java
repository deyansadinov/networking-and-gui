package com.clouway.networkingandgui.downloadagent.downloadagenttdd;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:30 Nov 14-11-27
 */
public class Main {
  public static void main(String[] args) {
    DownloadAgentUI downloadAgentUI = new DownloadAgentUI();
    DownloadAgent downloadAgent=new DownloadAgent(downloadAgentUI);

    downloadAgentUI.init(downloadAgent);


  }
}
