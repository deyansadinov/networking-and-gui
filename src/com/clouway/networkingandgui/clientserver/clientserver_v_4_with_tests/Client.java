package com.clouway.networkingandgui.clientserver.clientserver_v_4_with_tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:38 Dec 14-12-10
 */
public class Client {


  private boolean serverIsRunning = true;
  private Screen clientUI;

  private String message = "";

  public Client(Screen clientUI) {
    this.clientUI = clientUI;

  }

  public void connect(String host, int port) {
    try {
      final Socket socket = new Socket(host, port);

      new Thread(new Runnable() {
        @Override
        public void run() {
          while (serverIsRunning) {
            try {
              InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
              BufferedReader reader = new BufferedReader(inputStreamReader);
              message = reader.readLine();
              clientUI.display(message);
              if (reader.readLine() == null) {
                socket.setSoLinger(true, 0);
                serverIsRunning = false;
                socket.close();
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
