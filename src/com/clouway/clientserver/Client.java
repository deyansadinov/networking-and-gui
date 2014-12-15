package com.clouway.clientserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Client {
  private final String address;
  private final int port;
  private String message;

  public Client(String address, int port) {
    this.address = address;
    this.port = port;
  }

  public void start() {
    try {
      Socket socket = new Socket(address, port);
      BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
      BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());

      int bytes;
      while ((bytes = inputStream.read()) != -1) {
        System.out.println(bytes);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getMessage() {
    return message;
  }
}
