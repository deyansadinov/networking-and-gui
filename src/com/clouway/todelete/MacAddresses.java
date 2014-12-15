package com.clouway.todelete;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class MacAddresses {
  private String networkStartAddress;

  public MacAddresses(String networkStartAddress) {
    this.networkStartAddress = networkStartAddress;
  }

  public void scan() {
    String startIp = networkStartAddress.split("\\*")[0];

    for (Integer i = 1; i < 254; i++) {
      String host = startIp.concat(i.toString());
      if (isReachable(host)) {
        System.out.println(host);
      }
    }
  }

  public boolean isReachable(String subnet) {
    int timeout = 1000;
    try {
      return InetAddress.getByName(subnet).isReachable(timeout);
    } catch (IOException e) {
      return false;
    }
  }
}
