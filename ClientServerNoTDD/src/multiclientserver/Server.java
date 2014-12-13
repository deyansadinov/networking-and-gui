package multiclientserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created on 14-12-12.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class Server extends JFrame {


  JButton stopButton = new JButton("STOP");
  JTextArea textArea = new JTextArea();
  JTextField textField = new JTextField();

  ArrayList<Runner> clientHandler = new ArrayList<Runner>();

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
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {

            sendClosingMessage();

            closeConnection();

            System.exit(0);
          }
        });

      }
    });

  }

  public void startRunning() throws IOException {

    Runner runner;

    server = new ServerSocket(3333, 10);

    try {
      do {
        showMessage("\nWaiting for connection ... \n");
        connection = server.accept();

        showMessage("\nConnected to " + connection.getInetAddress() + "\n");

        runner = new Runner(connection);

        clientHandler.add(runner);
        System.out.println("LIST SIZE " + clientHandler.size());

        notifyForAddingNewMember();

      } while (true);

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }


  }


  public void showMessage(final String message) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        textArea.append(message);
      }
    });

  }


  private void notifyForAddingNewMember() {

    Integer clientNumber = clientHandler.size();
    String message = "Client added! Total: " + clientNumber;

    showMessage(message);
    for (Runner client : clientHandler) {
      client.sendMessage(message);
    }
  }
    private void sendClosingMessage() {

      String message = "SERVER - CLOSED";
      //showMessage("\nSERVER - CLOSED");

      for (Runner client : clientHandler) {
        client.sendMessage(message);
      }

    }


  private void closeConnection() {

    for (Runner client : clientHandler) {
      client.quit();
    }

  }



}
