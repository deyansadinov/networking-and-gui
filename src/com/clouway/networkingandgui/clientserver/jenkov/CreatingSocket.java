package com.clouway.networkingandgui.clientserver.jenkov;

import java.io.IOException;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:14 Dec 14-12-1
 */
public class CreatingSocket {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("127.0.0.1", 8080);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
