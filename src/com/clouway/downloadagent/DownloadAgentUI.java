package com.clouway.downloadagent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class DownloadAgentUI implements ActionListener, DownloadHandler, Runnable {
  private String[] buttons = {"download"};
  private JTextField textUrl;
  private JTextField textFilename;
  private JProgressBar progressBar = new JProgressBar();
  private JButton button;
  private Thread thread;
  private boolean isCancelClicked = false;
  private DownloadAgent downloadAgent;

  public void showUi() {
    JFrame frame = new JFrame("Super Calculator");
    frame.setPreferredSize(new Dimension(500, 100));

    textUrl = new JTextField();
    textUrl.setEditable(true);
    textUrl.setPreferredSize(new Dimension(100, 100));
    textUrl.setSize(new Dimension(200, 100));
    frame.add(textUrl);

    addDownloadButtonToPane(frame.getContentPane());
    textFilename = new JTextField();
    textFilename.setPreferredSize(new Dimension(100, 100));
    textFilename.setSize(new Dimension(200, 100));
    frame.add(textFilename);

    addProgressBarButton(frame.getContentPane());

    frame.setVisible(true);
    frame.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String urlContent;
    if (e.getActionCommand().equals("download")) {
      progressBar.setValue(0);
      thread = new Thread(this);
      thread.start();
      button.setText("Cancel");
    }

    if (e.getActionCommand().equals("Cancel")) {
      downloadAgent.cancel();
      progressBar.setValue(0);

      button.setText("download");
    }
  }

  @Override
  public void run() {
    String url = textUrl.getText();
    String filename = textFilename.getText();
    downloadAgent = new DownloadAgent(this, url, filename);
    downloadAgent.download();

  }

  @Override
  public void update(int totalBytes) {
    progressBar.setValue(totalBytes);
  }

  private void addDownloadButtonToPane(Container pane) {
    for (int i = 0; i < buttons.length; i++) {
      button = new JButton(buttons[i]);
      button.addActionListener(this);
      pane.add(button);
    }
  }

  private void addProgressBarButton(Container pane) {
    pane.setLayout(new GridLayout(2, 2));

    progressBar.setValue(0);
    progressBar.setStringPainted(true);
    Border border = BorderFactory.createTitledBorder("Downloading...");
    progressBar.setBorder(border);
    pane.add(progressBar);
  }
}
