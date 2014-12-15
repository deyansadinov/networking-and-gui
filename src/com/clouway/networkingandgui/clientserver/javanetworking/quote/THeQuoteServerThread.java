package com.clouway.networkingandgui.clientserver.javanetworking.quote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:31 Dec 14-12-4
 */
public class THeQuoteServerThread extends Thread {
  protected DatagramSocket socket = null;
  protected BufferedReader in = null;
  protected boolean moreQuotes = true;

  public THeQuoteServerThread() throws SocketException {
    this("Server");
  }

  public THeQuoteServerThread(String name) throws SocketException {
    super(name);
    try {
      socket = new DatagramSocket(8080);
      in = new BufferedReader(new FileReader("server.txt"));
    } catch (FileNotFoundException e) {
      System.err.println("Could not open quote file. Serving time instead.");
    }
  }

  public void run() {
    while (moreQuotes) {
      try {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        socket.receive(packet);
        String dString = null;
        if (in == null) {
          dString = new Date().toString();
        } else {
          dString = getNextQuote();
        }
        buf = dString.getBytes();
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
      } catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }

  private String getNextQuote() {
    String returnValue = null;
    try {
      if ((returnValue = in.readLine()) == null) {
        in.close();
        moreQuotes = false;
        returnValue = "No more quotes. Goodbye.";
      }
    } catch (IOException e) {
      returnValue = "IOException occurred in server.";
    }
    return returnValue;
  }
}
