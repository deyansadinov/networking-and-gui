package suportingmultipleclients;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientView extends JFrame implements Screen {


  private DefaultListModel<String> list = new DefaultListModel<String>();
  private String host;
  private int port;

  public void createFrame(final Client client, final String host, final int port) {
    this.host = host;
    this.port = port;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setSize(450, 230);

    JPanel panel = new JPanel();

    JPanel clientPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JList<String> listClient = new JList<String>(list);
    JScrollPane scrollPane = new JScrollPane(listClient);
    clientPanel.add(scrollPane,BorderLayout.CENTER);

    listClient.setPreferredSize(new Dimension(400,280));
    listClient.setBorder(new TitledBorder("Client :"));

    panel.add(clientPanel);
    panel.setBackground(Color.ORANGE);

    JButton connect = new JButton("Connect");
    this.add(connect, BorderLayout.SOUTH);
    this.add(panel);
    this.setVisible(true);


    connect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        client.connect(host,port);
      }
    });
  }



  @Override
  public void display(String text) {
    list.addElement(text);
  }
}
