package suportingmultipleclients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ServerView extends JFrame implements ServerMessageListener {

  private DefaultListModel<String> listModel = new DefaultListModel<String>();
  private Server server;



  public ServerView() throws HeadlessException {
    super("SERVER");
    createFrame();
  }

  private void createFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(450, 250);
    this.setLayout(new BorderLayout());

    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JList<String> clientsList = new JList<String>(listModel);
    clientsList.setPreferredSize(new Dimension(400,300));
    JScrollPane scrollPane = new JScrollPane(clientsList);

    panel.add(scrollPane, BorderLayout.CENTER);

    JButton stopServer = new JButton("Server Disconnect");
    panel.add(stopServer,BorderLayout.SOUTH);

    stopServer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        server.stop();
        System.exit(0);
      }
    });

    panel.setBackground(Color.ORANGE);
    this.add(panel);
    this.setVisible(true);
//    this.pack();
  }

  public void setServer(Server server) {
    this.server = server;
  }

  @Override
  public void newClientWasConnected(String messages) {

    listModel.addElement(messages + "\n");
  }
}
