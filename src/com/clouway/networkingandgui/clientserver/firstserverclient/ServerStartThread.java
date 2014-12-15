package com.clouway.networkingandgui.clientserver.firstserverclient;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:46 Dec 14-12-5
 */
public class ServerStartThread extends Thread {


  private Server server;
  private int started = 0;

  public ServerStartThread(Server server) {

    this.server = server;
  }

  public void run() {

      server.openConnection();

  }
}
