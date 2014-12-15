package com.clouway.threads.producerconsumer.normal;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:56 Oct 14-10-31
 */
public class Producer extends Thread {
  private Holle holle;
  private int number;


  public Producer(Holle c, int number) {
    holle = c;
    this.number = number;
  }

  public void run() {
    for (int i = 0; i <= 5; i++) {
      holle.put(i);
      System.out.println("Producer #" + this.number
              + " addToList: " + i);
      try {
        sleep((int)(Math.random() * 100));
      } catch (InterruptedException e) {}
    }
  }
}
