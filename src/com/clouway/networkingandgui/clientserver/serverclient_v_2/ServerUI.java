package com.clouway.networkingandgui.clientserver.serverclient_v_2;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:11 Dec 14-12-8
 */
public class ServerUI extends JFrame implements ServerClientsDisplay {

  private DefaultListModel listModel = new DefaultListModel();
  private Thread threadStart;


  public void init(final Server server) {
    this.setSize(400, 300);
    this.setTitle("Server");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final JTextField serverPort = new JTextField("8080");
    serverPort.setBorder(new TitledBorder("PORT"));
    serverPort.setPreferredSize(new Dimension(100, 50));
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JList<String> clientsList = new JList<>();

    clientsList.setPreferredSize(new Dimension(400, 200));
    clientsList.setModel(listModel);
    clientsList.setLayout(new BorderLayout());
    clientsList.setBorder(new TitledBorder("Clients"));
    JPanel connectPanel = new JPanel();
    final JButton startServer = new JButton("Start Server");
    connectPanel.add(startServer);

    JPanel disconectPanel = new JPanel();
    JButton disconect = new JButton("Stop Server");

    disconectPanel.add(disconect);

    JPanel listPanel = new JPanel();
    listPanel.add(clientsList);


    panel.add(connectPanel);
    connectPanel.add(disconectPanel);
    disconectPanel.add(listPanel);
    listPanel.add(serverPort);
    this.add(panel);

    this.pack();
    this.setVisible(true);

    startServer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Server is started!");
        startServer.setEnabled(false);
        threadStart = new ServerStartThread(server, Integer.parseInt(serverPort.getText()));
        threadStart.start();

      }
    });
    disconect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Server stopped !");
        if (threadStart != null) {
          Thread threadStopServer = new ServerStopThread(server);
          threadStopServer.start();
        }
        try {
          Thread.sleep(1000);
          System.exit(0);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  @Override
  public void updateClientsList(String clientInfo) {
    listModel.addElement(clientInfo);
  }
}
