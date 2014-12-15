package com.clouway.threads.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:28 Nov 14-11-4
 */
public class Produc implements Runnable {

  private BlockingQueue blockingQueue;

  Produc(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    for (int i=1;i<=5;i++){
      System.out.println("Number from producer "+i);
//      try {
//        Thread.sleep(1000);
//      } catch (InterruptedException e) {}
      try {
        blockingQueue.put(i);
      } catch (InterruptedException e) {}
    }

  }
}
