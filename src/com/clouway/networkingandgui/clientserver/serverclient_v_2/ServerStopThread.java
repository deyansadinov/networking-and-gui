package com.clouway.networkingandgui.clientserver.serverclient_v_2;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:50 Dec 14-12-8
 */
public class ServerStopThread extends Thread {
  private Server server;

  public ServerStopThread(Server server) {
    this.server = server;
  }

  public void run() {
     server.serverClosing();
  }
}
