package com.clouway.networkingandgui.clientserver.firstserverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:29 Dec 14-12-4
 */
public class Server {

  private String IP = "172.16.188.17";
  private static int port = 8080;
  private static ServerUI serverUI;
  private InputStreamReader inputStreamReader;
  private BufferedReader bufferedReader;
  private PrintStream printStream = null;
  private Socket socket = null;
  private ServerSocket serverSocket = null;

  //  private Map<Socket, Integer> clients = new HashMap<>();
  private List<Socket> clients = new ArrayList<>();
  private int clientsCount = 0;

  public Server(ServerUI serverUI) {

    this.serverUI = serverUI;
  }

  public void openConnection() {

    try {
      serverSocket = new ServerSocket(port);

      while (true) {
        socket = serverSocket.accept();

        String userInfo = "User " + socket.getInetAddress().getHostName() +
                " joined the party!";
        String userInfoFoeServerUI = "User " + socket.getInetAddress().getHostName() + " : " + socket.getPort() +
                " connected!";

        System.out.println(userInfo);

        serverUI.userConectedUpdate(userInfoFoeServerUI);

        if (!clients.contains(socket)) {
          clients.add(socket);

        } else {
          System.out.println(socket.isClosed());
          socket.close();
        }

        printStream = new PrintStream(socket.getOutputStream());
        printStream.println("Hello " + userInfoFoeServerUI);
        printStream.flush();
        ServerClientReturn serverClientReturn = new ServerClientReturn(socket);
        Thread thread = new Thread(serverClientReturn);
        thread.start();
        System.out.println(socket.isClosed()
        );
        socket.close();
      }


    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public String getIP() {
    return IP;
  }

  public int getPort() {
    return port;
  }

  public void stopServer() {
    if (serverSocket != null) {
      try {
        printStream = new PrintStream(socket.getOutputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
      printStream.println("Server is stoped working !");
      printStream.flush();
      System.out.println(socket.isClosed());
      System.out.println("Stop the server");
      System.exit(0);
      return;
    }
    System.exit(0);
    return;

  }
}
