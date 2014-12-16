package test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerView extends JFrame implements ServerMessageListener {

  private JTextArea textArea;
  private JButton stopServer;
  private Server server;

  public ServerView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setSize(200, 200);

    textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane, BorderLayout.CENTER);

    stopServer = new JButton("Stop Server");
    add(stopServer, BorderLayout.SOUTH);

    stopServer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            server.stiopMessage();
            server.stopServer();
            System.exit(0);
          }
        }).start();
      }
    });

    setVisible(true);
  }

  public void setServer(Server server) {
    this.server = server;
  }

  @Override
  public void newClientWasConnected(String message) {
    textArea.append(message + "\n");
  }

}
