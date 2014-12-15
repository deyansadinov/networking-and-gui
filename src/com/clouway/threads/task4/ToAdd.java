package com.clouway.threads.task4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:16 Nov 14-11-3
 */
public class ToAdd extends Thread {
  private MyList myList;

  private Random random = new Random();

  public ToAdd(MyList myList) {

    this.myList = myList;
  }

  public void run() {
    synchronized (myList){
      while (true) {

        int randomNumber= ThreadLocalRandom.current().nextInt(1,100);
        myList.add(randomNumber);
        System.out.println(Thread.currentThread().getName() + " has added " +randomNumber );
        try {
          sleep(1000);
        } catch (InterruptedException e) {}

      }
    }

  }
}
