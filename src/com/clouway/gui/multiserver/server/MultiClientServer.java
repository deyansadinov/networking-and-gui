package com.clouway.gui.multiserver.server;


import com.clouway.gui.multiserver.client.EStates;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by clouway on 14-12-12.
 */
public class MultiClientServer {
  private final StatusListener listener;
  private final Executor executor;
  private ServerSocket server;
  private AtomicBoolean isListening;
  private Vector<Socket> connections;

  public MultiClientServer(StatusListener listener, Executor executor) {
    this.listener = listener;
    this.executor = executor;
  }

  public void start(Integer port) throws IOException {
    isListening = new AtomicBoolean(true);
    connections = new Vector<Socket>();
    server = new ServerSocket(port);
    executor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          while (isListening.get()) {
            Socket client = server.accept();
            connections.add(client);
            PrintWriter out = new PrintWriter(new BufferedOutputStream(client.getOutputStream()));
            out.println(EStates.CONNECTED.name());
            out.flush();
            listener.onStatusChanged(EStates.CONNECTED.name());
          }
        } catch (SocketException se) {
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void stop() {
    isListening.set(false);

    System.out.println("size: " + connections.size());

    if (server != null) {
      executor.execute(new Runnable() {
        @Override
        public void run() {
          try {
            Iterator<Socket> iterator = connections.iterator();
            while (iterator.hasNext()){
              Socket each = iterator.next();
              PrintWriter out = new PrintWriter(new BufferedOutputStream(each.getOutputStream()));
              out.println(EStates.DISCONNECTED.name());
              out.close();
              each.close();
              listener.onStatusChanged(EStates.DISCONNECTED.name());
            }
            server.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
    }
  }
}
