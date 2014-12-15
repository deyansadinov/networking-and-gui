package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:00 Dec 14-12-4
 */
public class MultycastServer {
  public static void main(String[] args) {

      try {
        new MulticastServerThread().start();
      } catch (IOException e) {
        e.printStackTrace();
      }


  }

}
