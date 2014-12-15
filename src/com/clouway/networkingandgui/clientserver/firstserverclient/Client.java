package com.clouway.networkingandgui.clientserver.firstserverclient;

import java.io.IOException;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:47 Dec 14-12-4
 */
public class Client {
  public static void main(String[] args) {

    try {
      for (int i=0;i<100000;i++){
        Socket socket = new Socket("172.16.188.17", 8080);

      }
//
//      boolean isConnected = true;
//      while (!socket.isClosed()) {
//        InputStreamReader inReader = new InputStreamReader(socket.getInputStream());
//        BufferedReader reader = new BufferedReader(inReader);
//        System.out.println(reader.readLine());
//        if (reader.readLine() == null) {
//          reader.close();
//          inReader.close();
//          return;
//
//        }
//      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
