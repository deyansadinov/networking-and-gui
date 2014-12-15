package com.clouway.threads.task5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:04 Nov 14-11-10
 */

public class TimeOutTable<K,V> {

  private Map<String, ThreadedAdd> timedTable = new HashMap<>();
  private final int timer;


  public TimeOutTable(int timer) {

    this.timer = timer;
  }


  public synchronized void put(K key, V value) {
    ThreadedAdd threadedAdd = new ThreadedAdd(value, timedTable, timer, key);
    Thread add = new Thread(threadedAdd);


    if (timedTable.get(key) != null) {
      timedTable.get(key).setActive(false);
    }

    timedTable.put((String) key, threadedAdd);
    add.start();

  }

  public void remove(String key) {
    timedTable.remove(key);
  }

  public void get(String key) {
    if (timedTable.get(key) != null) {
      timedTable.get(key).setSuspend(0);
    }
  }

}
