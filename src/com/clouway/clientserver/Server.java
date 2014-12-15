package com.clouway.clientserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Server implements Runnable {
  private int port;

  public Server(int port) {
    this.port = port;
  }

  @Override
  public void run() {
    Socket socket;
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      socket = serverSocket.accept();

      OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
      Calendar calendar = Calendar.getInstance();

      writer.write("Hello " + calendar.getInstance().getTime() + "\n");

      writer.flush();
      socket.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
