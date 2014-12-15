package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:26 Dec 14-12-3
 */
public class MyClient {
  public static void main(String[] args) {
    Socket socket = null;
    try {
      socket = new Socket("127.0.0.1", 8080);
      socket.setSoTimeout(15000);
      InputStream in = socket.getInputStream();
      StringBuilder builder = new StringBuilder();
      InputStreamReader reader = new InputStreamReader(in);

      for (int c = reader.read(); c != -1; c = reader.read()) {
        builder.append((char) c);
      }
      System.out.println(builder);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
