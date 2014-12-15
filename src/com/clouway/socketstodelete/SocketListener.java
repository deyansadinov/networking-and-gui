package com.clouway.socketstodelete;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public interface SocketListener {
  byte[] read();

  int write(byte[] content);
}
