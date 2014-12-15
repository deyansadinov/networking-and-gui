package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:50 Dec 14-12-1
 */
public class MultithreadedDaytimeServer {

  public static final int PORT = 8080;

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(PORT);
      while (true) {
        Socket connection = serverSocket.accept();
        Thread task = new DayTimeThread(connection);
        task.start();
      }
    } catch (IOException e) {
      System.err.println("ERROR 404 !?!");
    }
  }

  private static class DayTimeThread extends Thread {
    private Socket connection;

    public DayTimeThread(Socket connection) {

      this.connection = connection;
    }

    public void run() {
      try {
        Writer out=new OutputStreamWriter(connection.getOutputStream());
        Date now=new Date();
        out.write(now +"\r\n");
        out.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }finally {
        try {
          connection.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
