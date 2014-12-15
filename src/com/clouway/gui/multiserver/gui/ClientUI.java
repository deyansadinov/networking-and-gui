package com.clouway.gui.multiserver.gui;

import com.clouway.gui.multiserver.client.EStates;

import javax.swing.*;
import java.awt.*;

/**
 * Created by clouway on 14-12-15.
 */
public class ClientUI extends JFrame {

  public ClientUI() {
    setupUI();
    createUIComponents();
    this.setVisible(true);
  }

  private void setupUI() {
    this.setSize(250, 100);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setTitle("ClientUI");
  }

  private void createUIComponents() {
    JPanel mainPanel = new JPanel();
    JPanel statusPanel = new JPanel();
    JPanel connectPanel = new JPanel();
    JLabel statusLabel = new JLabel("Status: ");
    JButton connectBtn = new JButton("Connect");
    JTextField currentState = new JTextField("Disconnected", 15);

    currentState.setEditable(false);
    currentState.setBorder(null);

    statusPanel.add(statusLabel);
    statusPanel.add(currentState);
    connectPanel.add(connectBtn);

    mainPanel.add(statusPanel);
    mainPanel.add(connectPanel);

    this.add(mainPanel);
  }


  public static void main(String[] args) {
    new ClientUI();
  }

}
