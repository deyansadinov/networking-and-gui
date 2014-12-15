package com.clouway.threads.list;

import com.clouway.testingwithmocks.newservice.InvalidAgeException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:04 Nov 14-11-7
 */
public class ListAdder implements Runnable {


  private volatile List<Integer> mylist;
  private final int size;

  public ListAdder(List<Integer> mylist, int size) {
    this.mylist = mylist;
    this.size = size;
  }

  @Override
  public void run() {
    for (int i=0;i<10;i++){
      System.out.println(Thread.currentThread().getName()+" has added "+i);

        put(i);



    }
  }

  private void put(int i)  {
    while (mylist.size()==size){
      synchronized (mylist){
        System.out.println("The list is full ...");
        try {
          mylist.wait();
        } catch (InterruptedException e) {}
      }
    }
    synchronized (mylist){
      mylist.add(i);
      mylist.notifyAll();
    }
  }
}
