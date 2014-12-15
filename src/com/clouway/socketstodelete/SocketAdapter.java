package com.clouway.socketstodelete;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class SocketAdapter {
  private SocketListener socketListener;

  public SocketAdapter(SocketListener socketListener) {

    this.socketListener = socketListener;
  }

  public void getMessage() {
    socketListener.read();
  }
}
