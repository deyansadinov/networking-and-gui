package com.clouway.networkingandgui.clientserver.javanetworking.quote;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:15 Dec 14-12-4
 */
public class Client {
  public static void main(String[] args) {
    try {
      DatagramSocket socket=new DatagramSocket();

      byte[] buf=new byte[256];
      String address="172.16.188.17";

      DatagramPacket packet=new DatagramPacket(buf,buf.length, InetAddress.getByName(address),8080);
      socket.send(packet);

      packet=new DatagramPacket(buf,buf.length);
      socket.receive(packet);

      String received = new String(packet.getData(), 0, packet.getLength());
      System.out.println("Quote of the Moment: " + received);

      socket.close();

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
