package com.clouway.networkingandgui.clientserver.javanetworking.quote;

import java.net.SocketException;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:29 Dec 14-12-4
 */
public class QuoteServer {
  public static void main(String[] args) {
    try {
      new THeQuoteServerThread().start();
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }
}
