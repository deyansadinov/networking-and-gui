package downloadagent;





import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgentUI extends JFrame  {

  private JProgressBar progressBar;
  private final DownloadAgent downloadAgent;

  public DownloadAgentUI(DownloadAgent downloadAgent) throws HeadlessException {
    this.downloadAgent = downloadAgent;
    init();
  }

  public JProgressBar getProgressBar() {
    return progressBar;
  }

  public void init() {
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

    JButton download = new JButton("Download");
    mainPanel.add(download);

    progressBar = new JProgressBar(0,100);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);
    mainPanel.add(progressBar);

    download.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Runnable runnable = new Runnable() {
          @Override
          public void run() {
            downloadAgent.downloadFile(filePath.getText(),pageAddress.getText());
          }
        };
        Thread thread = new Thread(runnable);
        thread.start();
      }
    });



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(450,200);

  }



}
