package com.clouway.networkingandgui.clientserver.javanetworking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:02 Dec 14-12-4
 */
public class MulticastServerThread extends QuoteServerThread {



  private long FIVE_SECONDS = 500000;

  public MulticastServerThread() throws IOException {
    super("MulticastServerThread");
  }

  public void run() {
    while (moreQuotes) {
      try {
        byte[] buf = new byte[256];

        // construct quote
        String dString = null;
        if (in == null)
          dString = new Date().toString();
        else
          dString = getNextQuote();
        buf = dString.getBytes();

        // send it
        InetAddress group = InetAddress.getByName("172.16.188.17");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 7777);
        socket.send(packet);

        // sleep for a while
        try {
          sleep((long)(Math.random() * FIVE_SECONDS));
        } catch (InterruptedException e) { }
      } catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }
}
