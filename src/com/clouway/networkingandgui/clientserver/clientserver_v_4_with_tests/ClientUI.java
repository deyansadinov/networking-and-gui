package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:41 Dec 14-12-10
 */
public class ClientUI extends JFrame implements Screen {
  private DefaultListModel<String> listModel = new DefaultListModel<>();

  private String host;
  private int port;


  public void init(final Client client, final String host, final int port) {

    this.host = host;
    this.port = port;
    this.setTitle("Client");
    this.setSize(370, 280);
    JPanel panel = new JPanel();

    JPanel panelClientList=new JPanel();
    JList listClient = new JList();
    panelClientList.add(listClient);

    listClient.setPreferredSize(new Dimension(250, 250));
    listClient.setBorder(new TitledBorder("Client :"));
    listClient.setModel(listModel);
    JPanel panelButtonConnect = new JPanel();
    final JButton connectButton = new JButton("Connect");
    panelButtonConnect.add(connectButton);

    panel.add(panelClientList);
    panelClientList.add(panelButtonConnect);

    this.add(panel);
    this.setVisible(true);

    connectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        connectButton.setEnabled(false);
        client.connect(host,port);
      }
    });
  }

  @Override
  public void display(String text) {
    listModel.addElement(text);
  }
}
