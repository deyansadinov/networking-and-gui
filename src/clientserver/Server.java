package clientserver;


import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Server  {
  public static void main(String[] args) throws Exception{
    Server server = new Server();
    server.run();
  }

  private void run() throws Exception{

    ServerSocket srsocket = new ServerSocket(4444);
    Socket socket = srsocket.accept();
    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String message = bufferedReader.readLine();
    System.out.println(message);

    if (message != null){
      PrintStream printStream = new PrintStream(socket.getOutputStream());
      printStream.println("Message received");
    }
  }

}
