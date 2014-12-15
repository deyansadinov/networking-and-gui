package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Date;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:03 Dec 14-12-4
 */
public class QuoteServerThread extends Thread {

  protected DatagramSocket socket = null;
  protected BufferedReader in = null;
  protected boolean moreQuotes = true;

  public QuoteServerThread(String name) throws SocketException {

    try {
      socket = new MulticastSocket(7777);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      in = new BufferedReader(new FileReader("NewTestFile.txt"));
    } catch (FileNotFoundException e) {
      System.err.println("Could not open File.");
    }
  }

  public void run() {
    while (moreQuotes) {
      byte[] buf = new byte[256];

      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
      }
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
      try {
        socket.send(packet);
      } catch (IOException e) {
       // moreQuotes = false;
      }
    }
    socket.close();
  }

  String getNextQuote() {
    String returnValue = null;
    try {
      if ((returnValue = in.readLine()) == null) {
      ///  in.close();
        moreQuotes = false;
        returnValue = "No more quotes.";
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return returnValue;
  }
}
