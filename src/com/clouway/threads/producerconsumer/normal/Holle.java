package com.clouway.threads.producerconsumer.normal;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:57 Oct 14-10-31
 */
public class Holle {

  private int contents;
  private boolean available = false;

  public synchronized int get() {
    while (!available) {
      try {
        wait();
      } catch (InterruptedException e) {
      }
    }
    available = false;
    notifyAll();
    return contents;
  }

  public synchronized void put(int value) {
    while (available) {
      try {
        wait();
      } catch (InterruptedException e) {
      }
    }
    contents = value;
    available = true;
    notifyAll();
  }
}
