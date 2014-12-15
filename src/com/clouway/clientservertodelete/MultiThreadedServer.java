package com.clouway.clientservertodelete;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class MultiThreadedServer {
  private static int PORT = 1102;

  public static void main(String[] args) {
    while (true) {
      ServerSocket serverSocket = null;
      try {
        serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        Thread thread = new Thread(new DayTimeThread(socket));
        thread.start();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          serverSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
