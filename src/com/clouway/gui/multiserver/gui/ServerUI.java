package com.clouway.gui.multiserver.gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by clouway on 14-12-10.
 */
public class ServerUI extends JFrame implements ActionListener {
  private JTextArea textAreaStatus;
  private JButton btnStart;
  private JButton btnStop;
  private JPanel serverUI;

  public ServerUI()  {
    createUIComponents();
  }

  private void createUIComponents() {

    setContentPane(serverUI);
    setResizable(false);
    btnStop.setEnabled(false);
    setSize(300,350);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }


  public static void main(String[] args) {
    new ServerUI();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
