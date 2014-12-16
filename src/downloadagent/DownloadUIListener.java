package downloadagent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadUIListener extends JFrame implements DownloadProgressListener {

  private final DownloadAgent downloadAgent;
  private JProgressBar jProgressBar;
  private JButton download;

  public DownloadUIListener(DownloadAgent downloadAgent) throws HeadlessException {
    this.downloadAgent = downloadAgent;
    init();
  }

  private void init() {
    JPanel mainPanel = new JPanel(new FlowLayout());
    this.add(mainPanel);

    JLabel save = new JLabel("Save in");
    mainPanel.add(save);

    final JTextField filePath = new JTextField();
    filePath.setPreferredSize(new Dimension(200,30));
    mainPanel.add(filePath);

    JLabel downloadPage = new JLabel("Page address");
    mainPanel.add(downloadPage);

    final JTextField pageAddress = new JTextField();
    pageAddress.setPreferredSize(new Dimension(200,30));
    mainPanel.add(pageAddress);

    download = new JButton("Download");
    mainPanel.add(download);

    jProgressBar = new JProgressBar(0,100);
    jProgressBar.setValue(0);
    jProgressBar.setStringPainted(true);
    mainPanel.add(jProgressBar);

    download.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(){
          public void run(){
            download.setEnabled(false);
            downloadAgent.downloadFile(filePath.getText(),pageAddress.getText());
            download.setEnabled(true);
          }
        };
        thread.start();
      }
    });

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(450,200);
    this.pack();

  }

  @Override
  public void onProgressWasUpdate(int savedBytes, long sizeDownloadFile) {
    jProgressBar.setMaximum((int) sizeDownloadFile);
    jProgressBar.setValue(savedBytes);
  }
}
