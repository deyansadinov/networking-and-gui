package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:20 Dec 14-12-1
 */
public class DayTimeServer {
  public final static int PORT = 8080;



  public static void main(String[] args) {
    ServerSocket serverSocket=null;
    try {
      serverSocket = new ServerSocket(PORT);
      while (true) {

        Socket connection = serverSocket.accept();
        Writer out = new OutputStreamWriter(connection.getOutputStream());
        Date now = new Date();
        out.write(now.toString() +"\r\n");
        out.flush();
        connection.close();

      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if(serverSocket!=null){
        try {
          serverSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
