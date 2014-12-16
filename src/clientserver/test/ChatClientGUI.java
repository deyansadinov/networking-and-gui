package clientserver.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ChatClientGUI {

  //Globals
  private static ChatClient chatClient;
  public static String userName = "sdfsfs";

  //GUI Globals - Main Window
  public static JFrame mainWindow = new JFrame();
  private static JButton b_about = new JButton();
  private static JButton b_connect = new JButton();
  private static JButton b_disconnect = new JButton();
  private static JButton b_send = new JButton();
  private static JButton b_help = new JButton();
  private static JLabel l_massage = new JLabel("Message: ");
  public static JTextField tf_message = new JTextField(20);
  private static JLabel l_conversation = new JLabel();
  public static JTextArea ta_conversation = new JTextArea();
  private static JScrollPane sp_conversation = new JScrollPane();
  private  static JLabel l_online = new JLabel();
  public static JList jl_online = new JList();
  private static JScrollPane sp_online = new JScrollPane();
  private static JLabel l_loggedInAs = new JLabel();
  private static JLabel l_loggedInAsBox = new JLabel();

  //GUI Globals - Login Window
  public static JFrame loginWindow = new JFrame();
  public static JTextField tf_userNameBox = new JTextField();
  private static JButton b_enter = new JButton("ENTER");
  private static JLabel l_enterUserName = new JLabel("Enter  username");
  private static JPanel p_login = new JPanel();

  public static void main(String[] args) {
    BuildMainWindow();
    Initialize();
  }

  public static void Initialize() {
    b_send.setEnabled(false);
    b_disconnect.setEnabled(false);
    b_connect.setEnabled(true);
  }

  public static void BuildMainWindow() {
    mainWindow.setTitle(userName + "'s Chat Box");
    mainWindow.setSize(450,500);
    mainWindow.setLocation(220, 180);
    mainWindow.setResizable(false);
    ConfigureMainWindow();
    MainWindowAction();
    mainWindow.setVisible(true);
  }

  public static void MainWindowAction() {
    b_send.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action_b_send();
      }
    });

    b_disconnect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action_b_disconnect();
      }
    });

    b_connect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        buildLogInWindow();
      }
    });

    b_help.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action_b_help();
      }
    });

    b_about.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action_b_help();
      }
    });


  }

  public static void buildLogInWindow() {
    loginWindow.setTitle("What's your name?");
    loginWindow.setSize(400,100);
    loginWindow.setLocation(250,200);
    loginWindow.setResizable(false);
    p_login =  new JPanel();
    p_login.add(l_enterUserName);
    p_login.add(tf_userNameBox);
    p_login.add(b_enter);
    loginWindow.add(p_login);

    loginAction();
    loginWindow.setVisible(true);
  }



  public static void action_b_help() {
    JOptionPane.showMessageDialog(null,"Multi-Client CHAT Program\n Deyan Sadinov 2014");
  }

  public static void action_b_disconnect() {
    try{
      chatClient.disconnect();
    }catch (Exception x){x.printStackTrace();}
  }

  public static void action_b_send() {
    if (!tf_message.getText().equals("")){
      chatClient.send(tf_message.getText());
      tf_message.requestFocus();
    }
  }

  public static void ConfigureMainWindow() {
    mainWindow.setBackground(Color.ORANGE);
    mainWindow.setSize(500,320);
    mainWindow.getContentPane().setLayout(null);

    b_send.setBackground(Color.CYAN);
    b_send.setForeground(Color.DARK_GRAY);
    b_send.setText("SEND");
    mainWindow.getContentPane().add(b_send);
    b_send.setBounds(250,40,81,25);

    b_disconnect.setBackground(Color.CYAN);
    b_disconnect.setForeground(Color.DARK_GRAY);
    b_disconnect.setText("DISCONNECT");
    mainWindow.getContentPane().add(b_disconnect);
    b_disconnect.setBounds(10,40,110,25);

    b_connect.setBackground(Color.CYAN);
    b_connect.setForeground(Color.DARK_GRAY);
    b_connect.setText("CONNECT");
    b_connect.setToolTipText("");
    mainWindow.getContentPane().add(b_connect);
    b_connect.setBounds(130,40,110,25);

    b_help.setBackground(Color.CYAN);
    b_help.setForeground(Color.DARK_GRAY);
    b_help.setText("HELP");
    mainWindow.getContentPane().add(b_help);
    b_help.setBounds(420,40,70,25);

    b_about.setBackground(Color.CYAN);
    b_about.setForeground(Color.DARK_GRAY);
    b_about.setText("ABOUT");
    mainWindow.getContentPane().add(b_about);
    b_about.setBounds(340,40,75,25);


    l_massage.setText("Message : ");
    mainWindow.getContentPane().add(l_massage);
    l_massage.setBounds(10,10,60,20);

    tf_message.setForeground(Color.CYAN);
    tf_message.requestFocus();
    mainWindow.getContentPane().add(tf_message);
    tf_message.setBounds(70,4,266,30);

    l_conversation.setHorizontalAlignment(SwingConstants.CENTER);
    l_conversation.setText("Conversation");
    mainWindow.getContentPane().add(l_conversation);
    l_conversation.setBounds(100,70,140,16);

    ta_conversation.setColumns(20);
    ta_conversation.setFont(new Font("Tahoma",0,12));
    ta_conversation.setForeground(Color.DARK_GRAY);
    ta_conversation.setLineWrap(true);
    ta_conversation.setRows(5);
    ta_conversation.setEditable(false);

    sp_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    sp_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    sp_conversation.setViewportView(ta_conversation);
    mainWindow.getContentPane().add(sp_conversation);
    sp_conversation.setBounds(10,90,330,180);

    l_online.setHorizontalAlignment(SwingConstants.CENTER);
    l_online.setText("Currently online ");
    l_online.setToolTipText("");
    mainWindow.getContentPane().add(l_online);
    l_online.setBounds(350,70,130,16);

    jl_online.setForeground(Color.DARK_GRAY);

    sp_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    sp_online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    sp_online.setViewportView(jl_online);
    mainWindow.getContentPane().add(sp_online);
    sp_online.setBounds(350,90,130,180);

    l_loggedInAs.setFont(new Font("Tahoma",0,12));
    l_loggedInAs.setText("Current logged in as");
    mainWindow.getContentPane().add(l_loggedInAs);
    l_loggedInAs.setBounds(348,0,140,15);

    l_loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
    l_loggedInAsBox.setFont(new Font("Tahoma",0,12));
    l_loggedInAsBox.setForeground(Color.DARK_GRAY);
    l_loggedInAsBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    mainWindow.getContentPane().add(l_loggedInAsBox);
    l_loggedInAsBox.setBounds(340,17,150,20);

  }

  public static void loginAction(){
    b_enter.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action_b_enter();
      }
    });
  }

  public static void action_b_enter() {
    if (!tf_userNameBox.getText().equals("")){
      userName = tf_userNameBox.getText().trim();
      l_loggedInAsBox.setText(userName);
      ChatServer.currentUsers.add(userName);
      mainWindow.setTitle(userName + "'s Chat Box");
      loginWindow.setVisible(false);
      b_send.setEnabled(true);
      b_disconnect.setEnabled(true);
      b_connect.setEnabled(false);
      Connect();
    }else {
      JOptionPane.showMessageDialog(null,"Please enter a name");
    }
  }

  public static void Connect() {
    try{
      final int port = 4444;
      final String host = "localhost";
      Socket socket = new Socket(host,port);
      System.out.println("You connected to : " + host);

      chatClient = new ChatClient(socket);

      //Send name to add to online list
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      out.println(userName);
      out.flush();

      Thread thread = new Thread(String.valueOf(chatClient));
      thread.start();
    }catch (Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null,"Server not responding");
      System.exit(0);
    }
  }

}
