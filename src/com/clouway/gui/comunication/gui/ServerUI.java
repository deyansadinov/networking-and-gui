package com.clouway.gui.comunication.gui;


import com.clouway.gui.comunication.server.MultiClientServer;
import com.clouway.gui.comunication.server.StatusListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Created by clouway on 14-12-10.
 */
public class ServerUI extends JFrame implements ActionListener, StatusListener {
  private int port;
  private JTextArea textAreaStatus;
  private JButton btnStart;
  private JButton btnStop;
  private JPanel serverUI;
  private JTextField portField;

  private MultiClientServer server;

  public ServerUI() {
    createUIComponents();
    btnStart.addActionListener(this);
    btnStop.addActionListener(this);
  }

  @Override
  public void onStatusChanged(String status) {
    textAreaStatus.append(status);
    textAreaStatus.append("\n");
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnStart) {
      server = new MultiClientServer(this, Executors.newFixedThreadPool(3));
      try {
        server.start(Integer.parseInt(portField.getText()));
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        portField.setEnabled(false);
        textAreaStatus.setText("");
      } catch (NumberFormatException ne) {
        JOptionPane.showMessageDialog(this, "Incorrect port");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }

    if (e.getSource() == btnStop) {
      server.stop();
      btnStart.setEnabled(true);
      btnStop.setEnabled(false);
      portField.setEnabled(true);
    }

  }

  private void createUIComponents() {
    setContentPane(serverUI);
    setResizable(false);
    btnStop.setEnabled(false);
    setSize(300, 350);
    setTitle("ServerUI");

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new ServerUI();
  }


}
