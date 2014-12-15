package com.clouway.networkingandgui.clientinformation.clientinformation_v_1;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:08 Dec 14-12-15
 */
public class ServerMain {
  public static void main(String[] args) {
    ServerUI serverUI=new ServerUI();
    Server server=new Server(8080,serverUI);

    serverUI.init(server);
  }
}
