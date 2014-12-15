package com.clouway.threads.task4;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:13 Nov 14-11-3
 */
public class Main {
  public static void main(String[] args) {
    MyList myList = new MyList();
    Thread threadAdd = new Thread(new ToAdd(myList), "Add :");
    //Thread threadRemove = new Thread(new ToRemove(myList), "Remove :");

    threadAdd.start();
    //threadRemove.start();

  }
}
