package com.clouway.clientserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Server {
  private int port;

  public Server(int port) {
    this.port = port;
  }

  public void start() {
    Socket socket = null;
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      socket = serverSocket.accept();

      OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
      writer.write("Hello\n");

      writer.flush();
      socket.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
