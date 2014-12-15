package com.clouway.socketstodelete;

import java.io.*;
import java.net.Socket;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class SimpleSockets {
  private static final String HOST = "dict.org";
  private static final int PORT = 1102;
  public static final String SERVER = "localhost";
  //  public static final int PORT = 2628;
  public static final int TIMEOUT = 1500;

  public static void main(String[] args) {

    Socket socket = null;
    Writer writer = null;
    BufferedReader reader = null;
    try {
      socket = new Socket(SERVER, PORT);
      socket.setSoTimeout(TIMEOUT);

      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

      reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

      String[] arg = {"date"};
      for (String word : arg) {

        define(word, writer, reader);
//        Thread.sleep(2000);
      }
//      writer.write("QUIT\r\n");
      writer.flush();
    } catch (IOException ex) {
      System.err.println(ex);
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  private static void define(String command, Writer writer, BufferedReader bufferedReader) {
    try {
      writer.write(command + "\n");
      writer.flush();
      for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
