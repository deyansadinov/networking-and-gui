package com.clouway.networkingandgui.clientserver.serverclient_tdd_v_3;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:23 Dec 14-12-9
 */
public class ServerMain {
  public static void main(String[] args) {
    ServerUI serverServerUI = new ServerUI();
    Server server = new Server(8080, serverServerUI);

    serverServerUI.init(server);

  }
}
