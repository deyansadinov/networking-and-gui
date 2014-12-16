package serverclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerView extends JFrame implements ServerMessageListener {

  private JTextArea textArea;
  private JButton stopServer;
  private Server server;

  public ServerView() throws HeadlessException {
    super("SERVER");
    greatFrame();
  }

  public void greatFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setSize(300, 300);

    textArea = new JTextArea();
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    this.add(scrollPane, BorderLayout.CENTER);

    stopServer = new JButton("Stop Server");
    this.add(stopServer, BorderLayout.SOUTH);

    stopServer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            server.stopServer();
            System.exit(0);
          }
        }).start();
      }
    });
    this.setVisible(true);


  }

  public void setServer(Server server) {
    this.server = server;
  }

  @Override
  public void newClientWasConnected(String message) {
    textArea.append(message + "\n");
  }
}
