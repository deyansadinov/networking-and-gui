package com.clouway.clientservertodelete;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Server {
  public static void main(String[] args) {
    while (true) {
      try (ServerSocket serverSocket = new ServerSocket(3000)) {
        Socket socket = serverSocket.accept();

        OutputStreamWriter ou = new OutputStreamWriter(socket.getOutputStream());
        Date now = new Date();

//        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        String read = buf.readLine();


//        if (read.equals("date")) {
          ou.write(now.toString() + " \n");
//        } else {
//          ou.write("ne \n");
//        }

//        if (read.contains("quit")) {
//          break;
//        }

//      InputStreamReader inputStreamReader = socket.getInputStream();
        ou.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}