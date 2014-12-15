package com.clouway.threads.task4new;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:17 Nov 14-11-7
 */
public class ListRemover implements Runnable {

  private final List<Integer> mylist;
  private final int size;

  public ListRemover(List<Integer> mylist, int size) {
    this.mylist = mylist;
    this.size = size;
  }

  @Override
  public void run() {
    while (true) {
      try {
        System.out.println(Thread.currentThread().getName() + " removed " + remove());
       Thread.sleep(10);
     } catch (InterruptedException e) {
      }
    }
  }

  private  int remove(){
    synchronized (mylist) {
      while (mylist.isEmpty()) {
        System.out.println("The list is empty ...");

        try {
          mylist.wait();
        } catch (InterruptedException e) {

        }
      }
      mylist.notify();
      return mylist.remove(0);
    }


  }
}
