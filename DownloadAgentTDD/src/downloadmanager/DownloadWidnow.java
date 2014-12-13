package downloadmanager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DownloadWidnow extends JFrame implements Display {

  class DownloadButtonHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      new Thread(new Runnable() {
        @Override
        public void run() {

          callDownloader();

        }
      }).start();

    }

  }

  private JTextField URLTextField = new JTextField();
  private final JButton downloadButton = new JButton();
  private JProgressBar progressBar = new JProgressBar();
  private JTextField nameTextField = new JTextField();
  private final JLabel nameLabel = new JLabel();
  private final JLabel URLLable = new JLabel();


  public DownloadWidnow() {

    super("DOWNLOAD AGENT");

    initComponents();

  }


  public void initComponents() {

    setLayout(null);
    setBounds(800, 300, 520, 230);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    downloadButton.setText("DOWNLOAD NOW");
    nameTextField.setToolTipText("");
    URLLable.setText("URL adress:");
    nameLabel.setText("Name:");

    downloadButton.setBounds(340, 110, 160, 25);
    URLTextField.setBounds(20, 40, 480, 25);
    progressBar.setBounds(20, 170, 480, 25);
    nameTextField.setBounds(20, 110, 280, 25);
    URLLable.setBounds(20, 10, 100, 25);
    nameLabel.setBounds(20, 80, 100, 25);

    add(URLTextField);
    add(downloadButton);
    add(progressBar);
    add(nameTextField);
    add(URLLable);
    add(nameLabel);


    DownloadButtonHandler handler = new DownloadButtonHandler();
    downloadButton.addActionListener(handler);

    progressBar.setStringPainted(true);


  }

  private void callDownloader() {

    final ErrorListener errorHandler = new ErrorHandler(URLTextField.getText(),URLTextField.getText(),this);

    final Downloader downloadAgent = new DownloadAgent(this,errorHandler);

    downloadAgent.processDownloading(URLTextField.getText(), nameTextField.getText());

  }

  @Override
  public void udateProgresBar(int value) {

    progressBar.setValue(value);
  }

  @Override
  public void showEmptyURLorFileFieldmessage() {

    JOptionPane.showMessageDialog(null,"Empty URL or Filename Field");
  }

  @Override
  public void showWrongURLAddress() {
    JOptionPane.showMessageDialog(null,"Wrong URL address");
  }

}
