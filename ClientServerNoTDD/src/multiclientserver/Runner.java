package multiclientserver;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created on 14-12-12.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class Runner {

  PrintWriter writer;

  Socket connection;


  public Runner(Socket connection) throws IOException {

    this.connection = connection;

    writer = new PrintWriter(connection.getOutputStream());
  }

  public void sendMessage(String message) {

    System.out.println(message);
    writer.println(message);
    writer.flush();


  }

  public void quit() {
    try {
      writer.close();
      connection.close();
    }catch (IOException ioe){

    }

  }
}
