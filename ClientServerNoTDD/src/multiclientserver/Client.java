package multiclientserver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created on 14-12-12.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class Client extends JFrame {

  JTextArea textArea = new JTextArea();
  JTextField textField = new JTextField();

  Scanner scanner;

  String host;
  private final Integer port;
  Socket connection;


  public Client(String host, Integer port) throws IOException {

    super("CLIENT");
    this.host = host;
    this.port = port;

    initComponents();


  }

  private void initComponents() {

    setSize(300, 200);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    add(textField, BorderLayout.NORTH);
    add(new JScrollPane(textArea));

  }

  public void startRunning() throws IOException, InterruptedException {


    connectToServer();
    setupStreams();
    processMessages();
    closeConnections();

  }

  private void connectToServer() throws IOException {
    boolean connected = false;

    showMessage("\nClient is trying to connect...\n");

    while (!connected) {
      try {
        connection = new Socket(host, port);
        connected = true;

      } catch (IOException ioe) {
        connected = false;

        try {
          Thread.sleep(1000);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
    showMessage("\nConnected to " + connection.getRemoteSocketAddress());

  }

  private void setupStreams() throws IOException {

    scanner = new Scanner(new BufferedInputStream(connection.getInputStream()));
  }

  private void processMessages() {
    String message = "";

    do {
      try {
        message = scanner.nextLine();
        showMessage("\n" + message);
      } catch (NoSuchElementException nsee) {
        System.out.println("client: cant read message");
        closeConnections();
        System.exit(0);
      }


    } while (!message.equals("SERVER - CLOSED"));
  }

  private void closeConnections() {
    try {

      scanner.close();
      connection.close();

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void showMessage(final String message) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        textArea.append(message);
      }
    });

  }

}
