package com.clouway.networkingandgui.downloadagent.agent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:30 Nov 14-11-29
 */
public class NewDownloadAgentUI extends JFrame implements PListener {

  private JPanel panel1;
  private JTextField textField1;
  private JTextField textField2;
  private JProgressBar progressBar1;
  private JButton downloadButton;
  private JButton cancelButton;
  private NewDownloadAgent newDownloadAgent;

  public void init(final NewDownloadAgent newDownloadAgent) {

    this.newDownloadAgent = newDownloadAgent;

    this.setSize(500, 100);
    textField1.setSize(60, 10);
    this.textField1.setText("/home/clouway/Desktop/car.jpg");
    cancelButton.setVisible(false);
    ActionListener downloadPressed = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textField1.setEditable(false);
        downloadButton.setEnabled(false);
        cancelButton.setVisible(true);

        newDownloadAgent.setTransferred(0);
        newDownloadAgent.setStop(false);

        final Thread downloadAgentThread = new DownloadThread(newDownloadAgent, textField1.getText(), textField2.getText());
        final Thread progressThread = new ProgressThread(newDownloadAgent);

        downloadAgentThread.start();
        progressThread.start();

        ActionListener cancelPressed = new AbstractAction() {
          @Override
          public void actionPerformed(ActionEvent e) {

            newDownloadAgent.setStop(true);

            downloadAgentThread.interrupt();
            progressThread.interrupt();

            textField1.setEditable(true);
            downloadButton.setEnabled(true);
            cancelButton.setVisible(false);
          }
        };
        cancelButton.addActionListener(cancelPressed);
      }
    };
    downloadButton.addActionListener(downloadPressed);

    this.add(panel1);
    this.setVisible(true);
  }

  @Override
  public void onProgressUpdate(int value) {
    progressBar1.setValue(value);
  }

  @Override
  public void setMax(int value) {
    progressBar1.setMaximum(value);
  }
}
