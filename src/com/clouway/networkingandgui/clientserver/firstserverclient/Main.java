package com.clouway.networkingandgui.clientserver.firstserverclient;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:16 Dec 14-12-4
 */
public class Main {

  public static void main(String[] args) {
    ServerUI serverUI = new ServerUI();
    Server server=new Server(serverUI);

   serverUI.init(server);
  }
}
