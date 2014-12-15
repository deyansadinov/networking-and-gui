package com.clouway.threads.task4;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:16 Nov 14-11-3
 */
public class ToRemove extends Thread {

  MyList myList;

  public ToRemove(MyList myList) {

    this.myList = myList;
  }

  public void run() {
    synchronized (myList){
      while (true) {
        myList.removeLastElement();
        try {
          sleep(3000);
        } catch (InterruptedException e) {}

      }
    }

  }
}
