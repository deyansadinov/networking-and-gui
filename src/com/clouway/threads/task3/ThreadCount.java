package com.clouway.threads.task3;

import java.lang.Thread.State;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:50 Oct 14-10-30
 */
public class ThreadCount {

  private boolean active = false;
  private int count;

  public synchronized int get() {
    while (!active) {
      try {
        wait();
      } catch (InterruptedException e) {
      }

    }
    active=false;
    notifyAll();
    return count;
  }

  public synchronized void put(int number){

    while (active){
      try {
        wait();
      } catch (InterruptedException e) {}
    }
    active=true;
    count=number;
    notifyAll();
  }

}
