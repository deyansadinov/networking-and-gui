package com.clouway.gui.multiserver.gui;

import com.clouway.gui.multiserver.client.Client;
import com.clouway.gui.multiserver.client.EStates;
import com.clouway.gui.multiserver.server.StatusListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Created by clouway on 14-12-15.
 */
public class ClientUI extends JFrame implements ActionListener, StatusListener {

  private JTextField currentStateField;
  private JTextField portField;
  private JButton connectBtn;
  private Client client;
  private JTextField ipField;

  public ClientUI() {
    setupUI();
    createUIComponents();
    connectBtn.addActionListener(this);
    this.setVisible(true);
  }

  private void setupUI() {
    this.setSize(300, 120);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setTitle("ClientUI");
  }

  private void createUIComponents() {
    JPanel mainPanel = new JPanel();
    JPanel statusPanel = new JPanel();
    JPanel portPanel = new JPanel();
    JPanel connectPanel = new JPanel();

    JLabel statusLabel = new JLabel("Status: ");
    JLabel portLabel = new JLabel("Port: ");
    JLabel ipLabel = new JLabel("IP: ");

    connectBtn = new JButton("Connect");

    currentStateField = new JTextField("Disconnected", 15);
    portField = new JTextField("4444", 4);
    ipField = new JTextField("127.0.0.1", 10);

    currentStateField.setEditable(false);
    currentStateField.setBorder(null);

    statusPanel.add(statusLabel);
    statusPanel.add(currentStateField);

    portPanel.add(ipLabel);
    portPanel.add(ipField);
    portPanel.add(portLabel);
    portPanel.add(portField);

    connectPanel.add(connectBtn);

    mainPanel.add(statusPanel);
    mainPanel.add(portPanel);
    mainPanel.add(connectPanel);

    this.add(mainPanel);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    connectBtn.setEnabled(false);
    client = new Client(this, Executors.newFixedThreadPool(2));
    try {
      client.connect(ipField.getText(), Integer.parseInt(portField.getText()));
      client.startListening();
    } catch (NumberFormatException nfe) {
      nfe.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void onStatusChanged(String status) {
    currentStateField.setText(status);
    if (EStates.DISCONNECTED.name().equals(status)) {
      connectBtn.setEnabled(true);
    }
  }

  public static void main(String[] args) {
    new ClientUI();
  }
}
