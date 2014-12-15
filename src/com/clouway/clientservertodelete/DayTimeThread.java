package com.clouway.clientservertodelete;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class DayTimeThread extends Thread {
  private Socket socket;

  public DayTimeThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(socket.getOutputStream());
      Date date = new Date();
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      String readLine = in.readLine();

      if (readLine.equals("date")) {
        out.write(date.toString());
      }


    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
