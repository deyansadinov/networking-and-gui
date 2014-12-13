package com.clouway.gui.multiserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executor;

/**
 * Created by clouway on 14-12-11.
 */
public class Client {

  private final IStatusListener listener;
  private final Executor executor;

  private class StateHandler {

    private final String state;
    private final IStatusListener listener;

    public StateHandler(String state, IStatusListener listener) {
      this.state = state;
      this.listener = listener;
    }


  }

  public Client(IStatusListener listener, Executor executor) {

    this.listener = listener;
    this.executor = executor;
  }

  private Socket socket;

  public void connect(String host, int port) throws IOException {
    socket = new Socket(host, port);
  }

  public void startListening() {
    executor.execute(new Runnable() {
      @Override
      public void run() {
        if (socket != null) {
          try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            handle(in.readLine());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  private void handle(String state) {
    listener.onStatusChanged(state);
    if ("Disconnected".equals(state)) {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
