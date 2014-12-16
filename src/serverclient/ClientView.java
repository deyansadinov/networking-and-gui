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

    this.setVisible(true);

  }

  @Override
  public void onNewMessageReceive(String message) {
    textArea.append(message + "\n");
  }
}
