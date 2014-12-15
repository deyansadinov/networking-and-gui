package com.clouway.socketstodelete;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
import java.io.*;
import java.net.*;

public class Socketer {

  public static void main(String[] args) {
    String[] urls = {"www.oreilly.com", "www.elharo.com", "login.ibiblio.org", "www.oreilly.com"};

    for (String url : urls) {

      Socket socket = null;

      try {
        socket = new Socket(url, 80);
        System.out.println("inet addr " + socket.getInetAddress() + "publiv port " + socket.getPort() + " local " + socket.getLocalPort());
//        socket.close();
      } catch (IOException e) {
        System.out.println("CAnnot connect" + socket.getInetAddress());
      }
    }


  }

}