package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:55 Dec 14-12-1
 */
public class LowPortScanner {
  public static void main(String[] args) {
    Socket socket =null;

    socket=new Socket();

    SocketAddress address=new InetSocketAddress("time.nist.gov",13);
    System.out.println();
    try {
      socket.connect(address);

      socket.setSoTimeout(10000);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
