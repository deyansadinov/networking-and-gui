package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:58 Dec 14-12-1
 */
public class PooledDayTimeServer {
  private static final int PORT=8080;

  public static void main(String[] args) {
    ExecutorService pool= Executors.newFixedThreadPool(50);
    try {
      ServerSocket  serverSocket=new ServerSocket(PORT);
      while (true){
        Socket connect=serverSocket.accept();
        Callable<Void> task=new DayTimeTask(connect);
        pool.submit(task);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class DayTimeTask implements Callable<Void> {
    private Socket connect;

    public DayTimeTask(Socket connect) {
      this.connect = connect;
    }

    @Override
    public Void call() throws Exception {
      Writer writer=new OutputStreamWriter(connect.getOutputStream());
      Date date=new Date();
      writer.write(date + "\r\n");
      writer.flush();
      return null;
    }
  }
}
