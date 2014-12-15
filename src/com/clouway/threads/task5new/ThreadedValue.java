package com.clouway.threads.task5new;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:15 Nov 14-11-12
 */

import java.util.Map;


public class ThreadedValue<K,T> extends Thread {
  private T value;
  private Map timeOutTable;
  private Integer timer;
  private K key;
  private boolean active = true;
  private int suspend = 0;

  public ThreadedValue(T value, Map timeOutTable, Integer timer, K key) {
    this.value = value;
    this.timeOutTable = timeOutTable;
    this.timer = timer;
    this.key = key;


  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public T getValue() {
    return value;
  }

  public void reset() {
    suspend = 0;
  }

  public void terminate() {
    suspend = timer;
    active = false;
  }

  public void run() {
    while (active) {
      System.out.printf(" %s %s time left %s \n", key, value, (timer - suspend));
      suspend++;

      if (suspend == timer) {
        timeOutTable.remove(key);
        active = false;
      }
      try {
        sleep(1000);
      } catch (InterruptedException e) {
      }

    }
  }


}
