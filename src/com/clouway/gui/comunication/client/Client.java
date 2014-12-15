package com.clouway.gui.comunication.client;

import com.clouway.gui.comunication.server.StatusListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executor;

/**
 * Created by clouway on 14-12-11.
 */
public class Client {

  private final StatusListener listener;
  private final Executor executor;
  private boolean isListening;

  public Client(StatusListener listener, Executor executor) {
    this.listener = listener;
    this.executor = executor;
  }

  private Socket socket;

  public void connect(String host, int port) throws IOException {
    socket = new Socket(host, port);
  }

  public void startListening() {
    isListening = true;
    executor.execute(new Runnable() {
      @Override
      public void run() {
        if (socket != null) {
          try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (isListening) {
              handle(in.readLine());
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  private void handle(String state) {
    listener.onStatusChanged(state);
    if (EStates.DISCONNECTED.name().equals(state)) {
      isListening = false;
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
