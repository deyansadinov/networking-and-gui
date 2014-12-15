package com.clouway.networkingandgui.clientserver.serverclient_v_2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:12 Dec 14-12-8
 */
public class Server {

  private ServerUI serverUI;
  private boolean serverRunning = true;
  private ServerSocket serverSocket = null;
  private Socket socket = null;
  private PrintStream printStream;
  private List<Socket> socketList = new ArrayList<>();
  private boolean isAlive = false;

  public Server(ServerUI serverUI) {

    this.serverUI = serverUI;
  }

  public void setAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }

  public synchronized void setServerRunning(boolean serverRunning) {
    this.serverRunning = serverRunning;
  }

  public boolean isServerRunning() {
    return serverRunning;
  }

  public  void openConnection(int PORT) {
    try {
      serverSocket = new ServerSocket(PORT);
      while (serverRunning) {
        socket = serverSocket.accept();

        printStream = new PrintStream(socket.getOutputStream());
        String Info = socket.getInetAddress() + " : " + socket.getPort();
        printStream.println("Hello " + Info+"\n");
        System.out.println(Info);
        printStream.flush();
        serverUI.updateClientsList("Client  " + Info + " is logged ON");
        socketList.add(socket);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        printStream.close();
        serverSocket.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public  void serverClosing() {
    for (Socket x : socketList) {
      try {
        printStream = new PrintStream(x.getOutputStream());
        printStream.println("Server is gone fishing !!!");
        printStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    try {

      socket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
