package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:41 Dec 14-12-10
 */
public class ClientMain {
  public static void main(String[] args) {
    ClientUI clientUI=new ClientUI();
    Client client=new Client(clientUI);

    clientUI.init(client,"172.16.188.17",8080);
  }
}
