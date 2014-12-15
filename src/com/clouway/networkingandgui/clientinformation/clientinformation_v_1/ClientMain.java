package com.clouway.networkingandgui.clientinformation.clientinformation_v_1;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:14 Dec 14-12-15
 */
public class ClientMain {
  public static void main(String[] args) {
    ClientUI clientUI=new ClientUI();

    Client client=new Client(clientUI);

    clientUI.init(client,"172.16.188.17",8080);
  }
}
