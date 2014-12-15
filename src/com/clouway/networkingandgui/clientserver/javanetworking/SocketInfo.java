package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:18 Dec 14-12-1
 */
public class SocketInfo {
  public static void main(String[] args) {

      try {
        Socket socket=new Socket("127.0.0.1",8080);
        System.out.println("Connect to "+socket.getInetAddress()+
        "on  port "+socket.getLocalPort()+" of "+socket.getLocalAddress());

      } catch (IOException e) {
        e.printStackTrace();

    }

  }
}
