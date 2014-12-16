package clientserver.test;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ChatClient implements Runnable {

  private final Socket sock;
  private  Scanner input;
  private Scanner send = new Scanner(System.in);
  private PrintWriter out;

  public ChatClient(Socket sock) {

    this.sock = sock;
  }

  @Override
  public void run() {
    try{
      try{
        input = new Scanner(sock.getInputStream());
        out = new PrintWriter(sock.getOutputStream());
        out.flush();
        checkStream();
      }finally {
        sock.close();
      }
    }catch (Exception x){
      System.out.println(x);}
  }

  public void checkStream() {
    while (true){
      receive();
    }
  }

  public void receive() {
    if (!input.hasNext()){
      String message = input.nextLine();

      if (message.contains("#?!")){
        String temp1 = message.substring(3);
               temp1 = temp1.replace("[","");
               temp1 = temp1.replace("]","");

        String[] currentUser = temp1.split(", ");
        ChatClientGUI.jl_online.setListData(currentUser);

      }else{
        ChatClientGUI.ta_conversation.append(message + "\n");
      }
    }
  }

  public void send(String text) {
    out.println(ChatClientGUI.userName + ": " + text);
    out.flush();
    ChatClientGUI.tf_message.setText("");
  }

  public void disconnect() throws IOException {
    out.println(ChatClientGUI.userName + "has disconnected");
    out.flush();
    sock.close();
    JOptionPane.showMessageDialog(null,"You are disconnected");
    System.exit(0);
  }

}
