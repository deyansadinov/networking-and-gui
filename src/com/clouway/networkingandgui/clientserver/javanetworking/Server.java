package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:24 Dec 14-12-4
 */
public class Server extends Thread {
  private int port = 8080;
  private ServerSocket serverSocket = null;
  private Socket socket = null;
  private InputStreamReader reader;
  private BufferedReader bufferedReader;
  private PrintStream printStream;

  public static void main(String[] args) {
    Server server = new Server();
    server.start();
  }

  public void setupConnection() {
    try {
      serverSocket = new ServerSocket(port);
      socket = serverSocket.accept();
      reader = new InputStreamReader(socket.getInputStream());
      bufferedReader = new BufferedReader(reader);
      printStream = new PrintStream(socket.getOutputStream());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    setupConnection();
    if (socket != null) {
      try {
        String message = bufferedReader.readLine();
        if (message != null) {
          printStream.println(message);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }
}
