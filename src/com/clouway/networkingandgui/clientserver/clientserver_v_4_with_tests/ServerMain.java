package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;


/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:23 Dec 14-12-9
 */
public class ServerMain {
  public static void main(String[] args) {
    ServerUI serverUI = new ServerUI();
    TheServer theServer = new TheServer(8080, serverUI);

    serverUI.init(theServer);

  }
}
