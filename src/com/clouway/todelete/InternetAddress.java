package com.clouway.todelete;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class InternetAddress {
  public static void main(String[] args) {
    String network = "192.168.1.*";
    MacAddresses macAddresses = new MacAddresses(network);
//    System.out.println(macAddresses.scan());
    macAddresses.scan();
  }
}
//fe80::222:64ff:fe23:75d1/64