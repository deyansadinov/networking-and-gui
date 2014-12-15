package com.clouway.networkingandgui.clientserver.serverclient_v_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:12 Dec 14-12-8
 */
public class Client {

  public static void main(String[] args) {
    Socket socket = null;
    BufferedReader reader = null;
    InputStreamReader inReader = null;
    try {
      socket = new Socket("172.16.188.17", 8080);
      socket.setSoLinger(true, 0);
      inReader = new InputStreamReader(socket.getInputStream());
      reader = new BufferedReader(inReader);
      while (true) {
        System.out.println(reader.readLine());
        if (reader.readLine() == null) {
          inReader.close();
          reader.close();
          socket.close();
          return;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
