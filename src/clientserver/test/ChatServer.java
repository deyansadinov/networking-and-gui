package clientserver.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ChatServer {

  public static ArrayList<Socket> connectionArray = new ArrayList<Socket>();
  public static ArrayList<String> currentUsers = new ArrayList<String>();

  public static void main(String[] args) {

    try{

      final int port = 4444;
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("Waiting for clients....");

      while (true){

        Socket socket = serverSocket.accept();
        connectionArray.add(socket);

        System.out.println("Client connected from: " + socket.getLocalAddress().getHostName());

        addUserName(socket);

        ChatServerReturn chat = new ChatServerReturn(socket);
        Thread thread = new Thread(String.valueOf(chat));
        thread.start();
      }

    } catch (IOException e) {
      System.out.println(e);
    }
  }

  private static void addUserName(Socket name) throws IOException {
    Scanner input = new Scanner(name.getInputStream());
    String username = input.nextLine();
    currentUsers.add(username);

    for (int i = 1; i < ChatServer.connectionArray.size(); i++) {
      Socket tempS = ChatServer.connectionArray.get(i - 1);
      PrintWriter out = new PrintWriter(tempS.getOutputStream());
      out.println("#?!" + currentUsers);
      out.flush();
    }
  }
}
