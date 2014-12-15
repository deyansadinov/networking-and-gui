package com.clouway.networkingandgui.clientserver.firstserverclient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:10 Dec 14-12-4
 */
public class ServerUI extends JFrame implements Display {

  private JList list1;
  private JPanel panel1;
  private JPanel panel2;
  private JButton disconectButton;
  private JButton buttonStartServer;
  private JScrollBar scrollBar1;

  private Server server;
  private Thread thread;
  private DefaultListModel listModel = new DefaultListModel();
  JScrollPane scrollPane;
  public void init(final Server server) {

    this.setTitle("Server - " + server.getIP() + " :  " + server.getPort());
    this.server = server;
    list1.setBorder(new TitledBorder("Users :"));
//    scrollPane.add(scrollBar1);
//    list1.add(scrollPane);
//    panel1.add(new scrollPane(list1, scrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
    scrollPane = new JScrollPane(list1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    panel2 = new JPanel();

    this.setSize(300, 300);

    this.add(panel1);
    this.setVisible(true);
    this.addWindowListener(new WindowListener() {
      @Override
      public void windowOpened(WindowEvent e) {

      }

      @Override
      public void windowClosing(WindowEvent e) {

      }

      @Override
      public void windowClosed(WindowEvent e) {

      }

      @Override
      public void windowIconified(WindowEvent e) {
        System.out.println("Iconified");

      }

      @Override
      public void windowDeiconified(WindowEvent e) {

      }

      @Override
      public void windowActivated(WindowEvent e) {
        System.out.println("Server is up and running.....");


      }

      @Override
      public void windowDeactivated(WindowEvent e) {

      }
    });
    buttonStartServer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        buttonStartServer.setEnabled(false);
        thread = new ServerStartThread(server);
        thread.start();

      }
    });
    disconectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        disconectButton.setEnabled(false);
        buttonStartServer.setEnabled(true);
        server.stopServer();

      }
    });
  }

  @Override
  public void userConectedUpdate(String name) {

    listModel.addElement(name);
  }
}
