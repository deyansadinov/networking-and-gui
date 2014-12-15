package com.clouway.networkingandgui.clientinformation.clientinformation_v_1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:31 Dec 14-12-13
 */
public class Server {


  private int port;
  private Screen screen;
  private ServerSocket serverSocket;
  private Socket socket;
  private boolean serverIsRunning = false;
  private Integer clientCout = 0;
  private final Vector<Socket> socketVector = new Vector<>();
  private PrintStream printStream;

  public Server(int port, Screen screen) {
    this.port = port;

    this.screen = screen;
  }

  public void openConnection() {
    try {
      serverSocket = new ServerSocket(port);
      serverIsRunning = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (serverIsRunning) {
            try {
              socket = serverSocket.accept();
              synchronized (socketVector) {
                socketVector.add(socket);
              }

              increaseCounter();
              String clientConnected = "Client :" + socket.getInetAddress() + " just joined.";

              screen.display(clientConnected);
              if (socketVector.size() > 1) {
                response();
              }
              printStream = new PrintStream(socket.getOutputStream());
              printStream.println(clientConnected + "\n");
              printStream.flush();

            } catch (IOException e) {
            }

          }
        }
      }).start();
    } catch (IOException e) {
      try {
        serverIsRunning = false;
        serverSocket.close();
        throw new ServerAlreadyRunningException();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  public void stop() {
    if (serverSocket == null) {
      return;
    }
    serverIsRunning = false;
    System.out.println(socketVector.size());
    synchronized (socketVector) {
      for (Socket x : socketVector) {
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

  public synchronized void increaseCounter() {
    clientCout++;
  }

  public void response() {
    screen.display("Response send.");
    for (Socket x : socketVector) {
      try {
        printStream = new PrintStream(x.getOutputStream());
        printStream.println("Client number: " + clientCout + " just joined" + "\n");
        printStream.flush();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

