package com.clouway.networkingandgui.clientserver.serverclient_v_2;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:10 Dec 14-12-8
 */
public class Main {
  public static void main(String[] args) {
    ServerUI serverUI =new ServerUI();
    Server server=new Server(serverUI);
    serverUI.init(server);
  }
}
