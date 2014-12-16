package clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Client {
  public static void main(String[] args) throws Exception{
    Client client = new Client();
    client.run();
  }

  private void run() throws Exception{
    Socket socket = new Socket("localhost",4444);
    PrintStream printStream = new PrintStream(socket.getOutputStream());
    printStream.println("Hello to Server from Client");

    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String message = bufferedReader.readLine();
    System.out.println(message);
  }
}
