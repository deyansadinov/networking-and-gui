package com.clouway.networkingandgui.clientserver.serverclient_v_2;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:36 Dec 14-12-8
 */
public class ServerStartThread extends Thread {
  private Server server;
  private int port;

  public ServerStartThread(Server server, int port) {
    this.server = server;
    this.port = port;
  }

  public void run() {
        server.openConnection(port);
  }
}
