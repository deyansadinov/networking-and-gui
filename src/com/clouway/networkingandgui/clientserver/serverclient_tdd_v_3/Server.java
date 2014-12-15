package com.clouway.networkingandgui.clientserver.serverclient_tdd_v_3;

import com.clouway.networkingandgui.clientserver.serverclient_tdd_v_3.customexceptions.ServerAlreadyRunningException;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:48 Dec 14-12-9
 */
public class Server {

  private int port;
  private Screen screen;
  private ServerSocket serverSocket = null;
  private Socket socket = null;
  private boolean serverIsRunning = false;
  private final List<Socket> socketList = new ArrayList<>();
  private PrintStream printStream;

  public Server(int port, Screen screen) {
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
              printStream.println("Hello " + socket.getInetAddress().toString() + " : " + socket.getPort() + "\n");
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

    serverIsRunning = false;
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

    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
