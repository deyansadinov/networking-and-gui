package com.clouway.threads.task2;

import java.util.concurrent.*;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:47 Oct 14-10-29
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    CountingThread firstCounter = new CountingThread(115);
    CountingThread secondCounter = new CountingThread(4);

    firstCounter.setTarget(secondCounter);
    secondCounter.setTarget(firstCounter);

    Thread thread1 = new Thread(firstCounter, "Thread One :");
    Thread thread2 = new Thread(secondCounter, "Thread Two :");

    thread1.start();
    thread2.start();


  }

}
