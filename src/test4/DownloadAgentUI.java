package test4;

import javax.swing.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgentUI extends JFrame implements ProgessListener {

  private JLabel url, downloadTo;
  private JTextField forUrl, forDownload;
  private JButton download, cancel;
  private JProgressBar jProgressBar;
  private DownladAgentThread downloadingThread;
  private String urlName;
//  private boolean runable = true;

  private DownloadAgent downloadAgent;

  public void init(final DownloadAgent downloadAgent) {
    this.downloadAgent = downloadAgent;
    this.setSize(700, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setTitle("Downloader");
    JPanel panel = new JPanel();
    url = new JLabel("Enter The URL");
    panel.add(url);
    forUrl = new JTextField("", 50);
    forUrl.setText("http://195.149.248.189:8080/2013-06-15/a07e3aecffe3daf4cabf07832ddd5686_920x0.jpg");
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
    final ActionListener actionListener = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        download.setVisible(false);
        forUrl.setEditable(false);
        cancel.setVisible(true);
        urlName = forUrl.getText();

        String newFileName = forDownload.getText() + "." + getExtention();
        downloadingThread = new DownladAgentThread(downloadAgent, urlName, newFileName);
        downloadAgent.setTransferred(0);
        downloadAgent.setStop(false);

        downloadingThread.start();

      }
    };
    download.addActionListener(actionListener);


    cancel = new JButton("Cancel");
    cancel.setVisible(false);

    ActionListener cancelPressed = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        downloadingThread.interrupt();
        downloadAgent.setStop(true);


        download.setVisible(true);
        forUrl.setEditable(true);
        cancel.setVisible(false);


      }
    };
    cancel.addActionListener(cancelPressed);

    panel3.add(jProgressBar);
    panel3.add(download);
    panel3.add(cancel);

    this.add(panel);
    panel.add(panel2);
    panel2.add(panel3);
    this.setVisible(true);
  }

  private String getExtention() {

    String[] ext = urlName.split("[.]");
    return ext[ext.length - 1];
  }

  @Override
  public void onProgressUpdate(int value) {
    jProgressBar.setValue(value);
  }

}
