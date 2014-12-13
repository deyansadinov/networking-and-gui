package downloadmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class DownloadProgressWindow extends JFrame {

	class CancelButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			continueToDownload=false;

		}
	}


	private boolean continueToDownload= true;

	public  boolean canContinueToDownload() {
		return continueToDownload;
	}

	private JProgressBar progressBar;


	public DownloadProgressWindow(JProgressBar progressBar) {

		super("DOWNLOAD PROGRES");

		this.progressBar=progressBar;

		init();

		arrangeComponents();
	}


	private void init() {


		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);


	}

	private void arrangeComponents() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Dialog", Font.BOLD, 14));
		progressBar.setBounds(33, 66, 387, 25);
		contentPane.add(progressBar);

		JLabel downloadLable = new JLabel("Downloading... ");
		downloadLable.setBounds(33, 28, 134, 15);
		contentPane.add(downloadLable);

		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setBounds(303, 23, 117, 25);
		contentPane.add(cancelButton);

		CancelButtonHandler cancelButtonHandler =new CancelButtonHandler();
		cancelButton.addActionListener(cancelButtonHandler);


	}



}
