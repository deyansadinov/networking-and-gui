package com.clouway.networkingandgui.downloadagent.downloadagenttdd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:42 Nov 14-11-27
 */
public class DownloadAgentUI extends JFrame implements ProgressListener {

  private JLabel url, downloadTo;
  private JTextField forUrl, forDownload;
  private JButton download, cancel;
  private JProgressBar jProgressBar;
  private int currentValue = 0;

  private DownloadListener downloadListener;

  public void init(final DownloadListener listener) {
    this.downloadListener = listener;
    this.setSize(700, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setTitle("Downloader");
    JPanel panel = new JPanel();
    url = new JLabel("Enter The URL");
    panel.add(url);
    forUrl = new JTextField("", 50);
    forUrl.setText("/home/clouway/Desktop/car.jpg");
    panel.add(forUrl);
    JPanel panel2 = new JPanel();
    downloadTo = new JLabel("Enter The name for the new file");
    panel2.add(downloadTo);
    forDownload = new JTextField("", 10);
    panel2.add(forDownload);
    jProgressBar = new JProgressBar();
    jProgressBar.setBorderPainted(true);
    jProgressBar.setStringPainted(true);


    JPanel panel3 = new JPanel();
    download = new JButton("DOWNLOAD NOW");
    ActionListener downloadButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == download) {
          listener.downloadStart(forUrl.getText(), forDownload.getText());

        }
      }
    };
    download.addActionListener(downloadButtonPressed);
    cancel = new JButton("Cancel");
    cancel.setVisible(false);


    panel3.add(jProgressBar);
    panel3.add(download);
    panel3.add(cancel);

    this.add(panel);
    panel.add(panel2);
    panel2.add(panel3);
    this.setVisible(true);
  }

  @Override
  public void onProgressUpdate( final int value) {
   // System.out.println(value);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        jProgressBar.setValue(value);

      }
    });


  }
}
