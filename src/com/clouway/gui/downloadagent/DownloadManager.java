package com.clouway.gui.downloadagent;


import com.clouway.gui.downloadagent.downloader.URLAddressException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by clouway on 14-11-24.
 */
public class DownloadManager extends JFrame implements ActionListener {
  private JProgressBar progressBar;
  private JTextField addressField;
  private JTextField addressFieldLabel;
  private JButton downloadBtn;
  private JPanel downloadPanel;
  private JButton cancelBtn;

  private Thread downloadingThread;

  public DownloadManager() {
    constructUI();
    setInitialStateForUI();
    downloadBtn.addActionListener(this);
    cancelBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Download action
    if (e.getSource() == downloadBtn) {
      if (displayWarningIfAddressFieldEmpty()) {
        return;
      }
      final String address = addressField.getText();

      final String fileName = promptUserForFileName();
      if (fileName == null) {
        return;
      }
      System.out.println("Helloooooo");
      progressBar.setValue(0);
      setWaitingStateForUI();

      downloadingThread = new Thread(new Runnable() {
        @Override
        public void run() {
          DownloadAgent downloadAgent = new DownloadAgent(new ProgressUpdateListener() {
            @Override
            public void onUpdate(int value) {
              System.out.println("value = " + value);
              progressBar.setValue(value);
            }
          });
          try {
            downloadAgent.download(address, fileName);
          } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(downloadPanel, e1.getMessage(), "Incorrect address", JOptionPane.ERROR_MESSAGE);

          } catch (URLAddressException e) {
            JOptionPane.showMessageDialog(downloadPanel, e.getMessage(), "Incorrect address", JOptionPane.ERROR_MESSAGE);

          }
          setInitialStateForUI();
        }
      });

      downloadingThread.start();
    }

    // Cancel action
    if (e.getSource() == cancelBtn) {
      downloadingThread.interrupt();
      setInitialStateForUI();
    }

  }

  private void constructUI() {
    setContentPane(downloadPanel);
    setVisible(true);
    setResizable(false);
    setTitle("Download Agent");
    pack();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    progressBar.setBorderPainted(true);
    progressBar.setStringPainted(true);
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
  }

  private void setInitialStateForUI() {
    downloadBtn.setEnabled(true);
    cancelBtn.setEnabled(false);
    setCursor(Cursor.getDefaultCursor());
//    addressField.setText("http://www.jbsystemsllc.com/blog/wp-content/uploads/2014/08/Panda-Penguin.jpg");
    addressField.setText("https://dl.google.com/android/adt/adt-bundle-linux-x86_64-20140702.zip");
//    addressField.setText("http://speedtest.reliableservers.com/10MBtest.bin");
  }

  private void setWaitingStateForUI() {
    downloadBtn.setEnabled(false);
    cancelBtn.setEnabled(true);
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
  }

  private boolean displayWarningIfAddressFieldEmpty() {
    if ("".equals(addressField.getText())) {
      JOptionPane.showMessageDialog(downloadPanel, "Enter address!");
      return true;
    }
    return false;
  }

  private String promptUserForFileName() {
    String fileName = JOptionPane.showInputDialog(downloadPanel, "Enter file name:", "name");
    return fileName;
  }

  public static void main(String[] args) {
    new DownloadManager();
  }
}
