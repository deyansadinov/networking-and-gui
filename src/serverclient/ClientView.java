package serverclient;

import download.ProgressListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientView extends JFrame implements ClientMessageListener {

  private JTextArea textArea;
  private JButton disconnect;
  private Client client;

  public ClientView() throws HeadlessException {
    super("CLIENT");
    greatFrame();
  }

  private void greatFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setSize(200, 200);

    textArea = new JTextArea();
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);
    this.add(scrollPane, BorderLayout.CENTER);

    disconnect = new JButton("Disconnect");
    this.add(disconnect, BorderLayout.SOUTH);

    disconnect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            if (Thread.currentThread().isInterrupted())
              client.disconnect();
            System.exit(0);
          }
        }).start();
      }
    });

    this.setVisible(true);

  }

  @Override
  public void onNewMessageReceive(String message) {
    textArea.append(message + "\n");
  }
}
