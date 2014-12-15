package com.clouway.todelete;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Demo {
  public static void main(String[] args) {
    ToDelete toDelete = new ToDelete("http://speedtest.reliableservers.com/10MBtest.bin", "gogo.bin");
    Thread t = new Thread(toDelete);
    t.start();

  }
}
