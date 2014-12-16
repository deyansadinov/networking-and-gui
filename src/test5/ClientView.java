package test5;

import javax.swing.*;
import java.awt.*;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientView extends JFrame implements ClientMessageListener  {

  private JTextArea textArea;

  public ClientView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setSize(200, 200);

    textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane, BorderLayout.CENTER);

    setVisible(true);
  }

  @Override
  public void onNewMessageReceived(String message) {
    textArea.append(message + "\n");
  }

}
