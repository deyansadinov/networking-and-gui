package clientserver.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ChatServerReturn implements Runnable {
  private final Socket sock;
  private Scanner input;
  private PrintWriter out;
  private String message = "";

  public ChatServerReturn(Socket x) {
    this.sock = x;
  }

  public void checkConnection() throws IOException{
    if (!sock.isConnected()){
      for (int i =1;i <= ChatServer.connectionArray.size();i++){
        if (ChatServer.connectionArray.get(i) == sock){
          ChatServer.connectionArray.remove(i);
        }
      }

      for (int i = 1;i <= ChatServer.connectionArray.size();i++){
        Socket tempSocket = ChatServer.connectionArray.get(i -1);
        PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
        tempOut.println(tempSocket.getLocalAddress().getHostName() + "disconnected");
        System.out.println(tempSocket.getLocalAddress().getHostName() + "disconnected");
      }
    }
  }

  @Override
  public void run() {
    try{
      try{
        input = new Scanner(sock.getInputStream());
        out = new PrintWriter(sock.getOutputStream());

        while (true){
          checkConnection();

          if (!input.hasNext()){return;}

          message = input.nextLine();
          System.out.println("Client said : " + message);

          for (int i =1;i <= ChatServer.connectionArray.size();i++){
            Socket tempSocket = ChatServer.connectionArray.get(i-1);
            PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream());
            tempOut.println(message);
            tempOut.flush();
            System.out.println("Sent to: " + tempSocket.getLocalAddress().getHostName());
          }
        }
     }finally {
        sock.close();
      }
    }catch (Exception x){
      System.out.println(x);}
  }
}
