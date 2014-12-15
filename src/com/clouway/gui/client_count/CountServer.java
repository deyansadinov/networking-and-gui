package com.clouway.gui.client_count;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

/**
 * Created by clouway on 14-12-15.
 */
public class CountServer {

  private final CountListener listener;
  private final Executor executor;
  private ServerSocket server;

  private int count;

  public CountServer(CountListener listener, Executor executor) {
    this.listener = listener;
    this.executor = executor;
  }

  public void start(int port) throws IOException {
    server = new ServerSocket(port);
    executor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Socket client = server.accept();
          listener.onCountChanged(++count);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

  }

  public void stop() {

  }
}
