package com.clouway.threads.task4new;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:04 Nov 14-11-7
 */
public class ListAdder implements Runnable {


  private final List<Integer> mylist;
  private final int size;

  public ListAdder(List<Integer> mylist, int size) {
    this.mylist = mylist;
    this.size = size;
  }

  @Override
  public void run() {
    for (int i = 0; i <= 10; i++) {
      System.out.println(Thread.currentThread().getName() + " has added " + i);

      put(i);
    }
  }

  private void put(int i) {
    synchronized (mylist) {
      while (mylist.size() == size) {

        System.out.println("The list is full ...");
        try {
          mylist.wait();
        } catch (InterruptedException e) {
        }
      }
      mylist.add(i);
      mylist.notifyAll();
    }
  }
}
