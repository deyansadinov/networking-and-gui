package com.clouway.threads.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:34 Nov 14-11-4
 */
public class Cons implements Runnable {

  private final BlockingQueue blockingQueue;

  public Cons(BlockingQueue blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    while (true){
      try {
        System.out.println("Consumer "+blockingQueue.take());

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
