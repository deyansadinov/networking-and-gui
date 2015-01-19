package suportingmultipleclients;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Client {

  //  private final UserMessages userMessages;
//  private final ClientMessageListener clientMessageListener;
  private boolean running = false;
//  private StringBuilder builder;
  private Screen screen;
  private Socket clientSocket;

//  public Client(UserMessages userMessages, ClientMessageListener clientMessageListener) {
//    this.userMessages = userMessages;
//    this.clientMessageListener = clientMessageListener;
  //}

  public Client(Screen screen) {
    this.screen = screen;
  }

  public void connect(String host, int port) {
    try {
      running = true;
      clientSocket = new Socket(host, port);
//      clientMessageListener.onResponseWasReceived(userMessages.connectClient());
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (running) {
            try {
              InputStream inputStream = clientSocket.getInputStream();
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//              clientMessageListener.onResponseWasReceived(reader.readLine());
              screen.display(reader.readLine());
              if (reader.readLine() == null) {
                running = false;
                inputStream.close();
                reader.close();

//                clientSocket.close();
                return;
              }

            } catch (IOException e) {
//              e.printStackTrace();
            }
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  public void disconnect() {
    running = false;
    screen.display("You are disconnected");
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
      writer.println("disconnecting");
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try{
        if( writer != null)
        writer.close();
        clientSocket.close();
      } catch (IOException e){

      }
    }

  }
}
