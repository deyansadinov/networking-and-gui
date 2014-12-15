package com.clouway.networkingandgui.clientinfirmation_v_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:07 Dec 14-12-15
 */
public class Client {


  private Screen screen;
  private boolean isAlive = false;
  private String host;
  private int port;

  public Client(Screen screen) {
    this.screen = screen;
  }

  public void connect(String host, int port) {
    this.host = host;
    this.port = port;
    try {
      final Socket socket = new Socket(this.host, this.port);
      socket.setSoLinger(true, 0);
      isAlive = true;
      new Thread(new Runnable() {
        @Override
        public void run() {
          while (isAlive) {
            try {
              InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
              BufferedReader reader = new BufferedReader(inputStreamReader);
              screen.display(reader.readLine());
              if (reader.readLine() == null) {
                isAlive = false;
                inputStreamReader.close();
                reader.close();
                socket.close();
                return;
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
