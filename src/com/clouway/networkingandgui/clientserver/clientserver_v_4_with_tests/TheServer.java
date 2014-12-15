package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;


import com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests.customexceptions.ServerAlreadyRunningException;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:48 Dec 14-12-9
 */
public class TheServer {


  private final int port;
  private final Screen screen;
  private ServerSocket serverSocket = null;
  private Socket socket = null;
  private boolean serverIsRunning = false;
  private final Vector<Socket> socketList = new Vector<>();
  private PrintStream printStream;

  public TheServer(int port, Screen screen) {

    this.port = port;
    this.screen = screen;
  }

  public synchronized void openConnection() {
    try {
      serverSocket = new ServerSocket(port);
      serverIsRunning = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (serverIsRunning) {
            try {
              socket = serverSocket.accept();
              screen.display(socket.getInetAddress().toString());
              printStream = new PrintStream(socket.getOutputStream());
              printStream.println("Hello client :" + socket.getInetAddress().toString() + "\n");
              printStream.flush();
              synchronized (socketList){
                socketList.add(socket);
              }

            } catch (SocketException e) {
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

        }
      }).start();
    } catch (IOException e) {
      throw new ServerAlreadyRunningException();
    }

  }


  public void stop() {
    if (serverSocket == null) {
      return;
    }
    serverIsRunning = false;
    System.out.println(socketList.size());
    synchronized (socketList){
      for (Socket x : socketList) {
        try {
          printStream = new PrintStream(x.getOutputStream());
          printStream.println("Server is gone fishing." + "\n");
          printStream.flush();

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    screen.display("Offline!");
    try {

      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
