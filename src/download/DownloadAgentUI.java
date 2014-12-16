package download;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgentUI extends JFrame implements ProgressListener {

  private Thread threadDownload;
  private JProgressBar progressBar;
  private JButton cancelButton;
  private DownloadAgent downloadAgent;


  public void init(final DownloadAgent downloadAgent) {
    this.downloadAgent = downloadAgent;


    JPanel mainPanel = new JPanel(new FlowLayout());
    this.add(mainPanel);

    JLabel save = new JLabel("Save in");
    mainPanel.add(save);

    final JTextField filePath = new JTextField();
    filePath.setPreferredSize(new Dimension(200, 30));
    mainPanel.add(filePath);

    JLabel downloadPage = new JLabel("Page address");
    mainPanel.add(downloadPage);

    final JTextField pageAddress = new JTextField();
    pageAddress.setText("http://195.149.248.189:8080/2013-06-15/a07e3aecffe3daf4cabf07832ddd5686_920x0.jpg");
    pageAddress.setPreferredSize(new Dimension(200, 30));
    mainPanel.add(pageAddress);

    final JButton download = new JButton("Download");
    mainPanel.add(download);


    progressBar = new JProgressBar(0, 100);
    progressBar.setValue(0);
    progressBar.setBorderPainted(true);
    progressBar.setStringPainted(true);
    mainPanel.add(progressBar);

    ActionListener downloadListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
         DownloadAgent downloadAgent = new DownloadAgent(new ProgressListener() {
          @Override
          public void onProgressWasUpdate(int savedBytes) {
            progressBar.setValue(savedBytes);
            download.setVisible(false);
            cancelButton.setVisible(true);
            pageAddress.setEditable(false);

          }
        });

        threadDownload = new ThreadDownload(downloadAgent, filePath.getText(), pageAddress.getText());

        downloadAgent.isInterrupted(false);
        threadDownload.start();
      }
    };
    download.addActionListener(downloadListener);

    cancelButton = new JButton("Cancel");
    cancelButton.setVisible(false);

    ActionListener cancelPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
        threadDownload.interrupt();
        downloadAgent.isInterrupted(true);

        pageAddress.setEditable(true);
        download.setVisible(true);
        cancelButton.setVisible(false);
      }
    };
    cancelButton.addActionListener(cancelPressed);
    mainPanel.add(cancelButton);




    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("DownloadAgent");
    mainPanel.setBackground(Color.ORANGE);
    setVisible(true);
    setSize(450, 200);
    pack();

  }


  @Override
  public void onProgressWasUpdate(int savedBytes) {
    progressBar.setValue(savedBytes);
  }
}
