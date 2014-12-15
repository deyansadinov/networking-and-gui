package com.clouway.threads.task5;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:09 Nov 14-11-10
 */
public class ThreadedAdd<T> extends Thread {

  private T value;
  private Map<String, ThreadedAdd> timedTable;
  private int timer;
  private T key;
  private boolean active = true;
  private Integer suspend = 0;

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setSuspend(Integer suspend) {
    this.suspend = suspend;
  }

  public ThreadedAdd(T value, Map<String, ThreadedAdd> timedTable, int timer, T key) {

    this.value = value;

    this.timedTable = timedTable;
    this.timer = timer;
    this.key = key;
  }

  public boolean getActive() {
    return active;
  }

  @Override
  public void run() {

    while (active) {

      System.out.println(Thread.currentThread().getName() + " "+key+" : " + value + "  " + (timer - suspend));
      suspend++;


      if (suspend == timer) {
        active = false;
        timedTable.remove(key);

      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println(":)");
        active = true;
        notifyAll();
      }


    }


  }



}
