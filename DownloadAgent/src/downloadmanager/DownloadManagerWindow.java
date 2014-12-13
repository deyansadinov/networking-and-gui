package downloadmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 14-11-25.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class DownloadManagerWindow extends JFrame {


  class DownloadButtonHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      String url = urlField.getText();
      String saveLocation = defaultDirectoryToSaveFile + "/" + nameField.getText();
      absoluteFilePathOfTheLastDownloadedFile = saveLocation;

      try {

        if (getResponseCode(url) != 404) {

          JProgressBar progressBar = new JProgressBar();
          URLDownloader urlDownloader = new URLDownloader(url, saveLocation, progressBar, new DownloadProgressWindow(progressBar) );

          Thread URLTread = new Thread(urlDownloader);
          Thread progresTread = new Thread(new ProgressBarMannager(progressBar, urlDownloader, URLTread));

          URLTread.start();
          progresTread.start();

        } else {
          JOptionPane.showMessageDialog(null, "UNEXISTING URL");
        }
      } catch (IOException e1) {
        JOptionPane.showMessageDialog(null, "UNEXISTING URL");
      }
    }
  }

  class DirectoryCoiceHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

      chooseDirectoryToSaveTheFiles();
    }
  }

  class ExitMenuItemHandlerer implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JOptionPane.showMessageDialog(null, "CLOSE THE APLICATION", "Closing...", JOptionPane.OK_OPTION);
      System.exit(1);
    }
  }

  class OpenLastDownloadedFileMenuHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      new Thread(new Runnable() {
        @Override
        public void run() {
          if (absoluteFilePathOfTheLastDownloadedFile!=null)
          openLastDownloadedFile();
        }
      }).start();
    }
  }

  private JTextField urlField = new JTextField();
  private JTextField nameField = new JTextField();
  private JButton   downloadCancelButton = new JButton("DOWNLOAD NOW");
  private JButton   directoryChoiceButton = new JButton("CHOOSE DIRECTORY");
  private JLabel    directoryChoiceLabel = new JLabel(new ImageIcon(getClass().getResource("/images/downloadicon.png")));
  private JMenuBar  menuBar = new JMenuBar();
  private JMenu     fileMenu = new JMenu("FILE");
  private JMenu     openMenu = new JMenu("OPEN");
  private JMenuItem exitMenuItem = new JMenuItem("EXIT");
  private JMenuItem openFileItem = new JMenuItem("OPEN LAST DOWNLOADED FILE");
  private String    defaultDirectoryToSaveFile = "/home/clouway/Desktop";
  private String    absoluteFilePathOfTheLastDownloadedFile = null;


  public DownloadManagerWindow() {

    initialize();
    setBounds();
    addComponents();
    processHandlers();

  }

  private void initialize() {
    setVisible(true);
    setTitle("DOWNLOAD MANAGER");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);


    urlField.setText("http://img0.mxstatic.com/wallpapers/d85b16a839edb781951237b9a1760a5d_large.jpeg");
    urlField.setColumns(10);

    nameField.setText("TYPE FILENAME");
    nameField.setColumns(10);


  }

  private void processHandlers() {
    DownloadButtonHandler downloadButtonHandler = new DownloadButtonHandler();
    DirectoryCoiceHandler directoryCoiceHandler = new DirectoryCoiceHandler();
    ExitMenuItemHandlerer exitMenuItemHandlerer = new ExitMenuItemHandlerer();
    OpenLastDownloadedFileMenuHandler openLastDownloadedFile = new OpenLastDownloadedFileMenuHandler();

    downloadCancelButton.addActionListener(downloadButtonHandler);
    directoryChoiceButton.addActionListener(directoryCoiceHandler);
    exitMenuItem.addActionListener(exitMenuItemHandlerer);
    openFileItem.addActionListener(openLastDownloadedFile);
  }

  private void setBounds() {

    setBounds(700, 300, 450, 200);
    nameField.setBounds(249, 120, 174, 25);
    urlField.setBounds(36, 34, 387, 25);
    downloadCancelButton.setBounds(249, 71, 174, 25);
    directoryChoiceButton.setBounds(36, 71, 174, 25);
    directoryChoiceLabel.setBounds(36, 110, 191, 50);
    menuBar.setBounds(0, 0, 450, 27);
    fileMenu.setBounds(1, 1, 50, 24);
    openMenu.setBounds(52, 1, 60, 24);


  }

  private void addComponents() {
    add(nameField);
    add(urlField);
    add(downloadCancelButton);
    add(directoryChoiceButton);
    add(directoryChoiceLabel);
    add(menuBar);
    menuBar.add(openMenu);
    menuBar.add(fileMenu);
    fileMenu.add(exitMenuItem);
    openMenu.add(openFileItem);

  }

  public int getResponseCode(String url) throws IOException {
    URL urlAddres = new URL(url);
    HttpURLConnection huc = (HttpURLConnection) urlAddres.openConnection();
    huc.setRequestMethod("GET");
    huc.connect();
    return huc.getResponseCode();
  }

  private void chooseDirectoryToSaveTheFiles() {

    JFileChooser fc = new JFileChooser();

    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int response = fc.showDialog(this, "Save to...");

    if (response == JFileChooser.APPROVE_OPTION) {
      defaultDirectoryToSaveFile = fc.getSelectedFile().toString();
      System.out.println(defaultDirectoryToSaveFile);
    }
  }

  private void openLastDownloadedFile() {
    Desktop desktop = Desktop.getDesktop();
    try {
      desktop.open(new File(absoluteFilePathOfTheLastDownloadedFile));
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

}