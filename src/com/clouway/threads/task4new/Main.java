package com.clouway.threads.task4new;

import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 8:52 Nov 14-11-7
 */
public class Main {
  public static void main(String[] args) {

    List<Integer> mylist = new ArrayList<>();
    int size = 3;

    Thread addToList = new Thread(new ListAdder(mylist, size), "Add : ");
    Thread removeFromList = new Thread(new ListRemover(mylist, size), "Remove :");

    addToList.setPriority(8);
    removeFromList.setPriority(1);

    addToList.start();
    removeFromList.start();

  }
}
