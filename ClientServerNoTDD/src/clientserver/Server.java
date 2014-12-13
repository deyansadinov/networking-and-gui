package clientserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 14-12-9.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class Server extends JFrame {

  JButton stopButton = new JButton("STOP");
  JTextArea textArea = new JTextArea();
  JTextField textField = new JTextField();

  ServerSocket server;
  Socket connection;

  PrintWriter writer;



  public Server() throws IOException {

    super("SERVER");

    initComponents();


  }

  private void initComponents() {

    setSize(300, 200);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    add(stopButton, BorderLayout.SOUTH);
    add(textField, BorderLayout.NORTH);
    add(new JScrollPane(textArea));

    stopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        sendClosingMessage();

        closeConnection();

        System.exit(0);
      }
    });

  }
  public void startRunning() {
    try {

      waitForConnection();
      setupTheStream();
      sendTheHelloMessage();

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }


  }

  private void closeConnection() {
    try {
      writer.close();
      connection.close();
    }catch (IOException ioe){}


  }

  private void sendTheHelloMessage() {
    writer.println("SERVER - HELLO\n");
    writer.flush();
    showMessage("\nSERVER - HELLO");
  }

  private void setupTheStream() {

    try {
      writer = new PrintWriter(connection.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  private void waitForConnection() throws IOException {

    showMessage("\nWaiting for connection ... \n");

    server = new ServerSocket(4567, 10);
    connection = server.accept();

    showMessage("\nConnected to "+connection.getInetAddress());
  }


  private void showMessage(final String message) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        textArea.append(message);
      }
    });

  }


  private void sendClosingMessage(){

    writer.print("SERVER - CLOSED");
    writer.flush();
    showMessage("\nSERVER - CLOSED");

  }


}
