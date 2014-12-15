package com.clouway.threads.task5new;

import java.util.Objects;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:13 Nov 14-11-12
 */
public class Main {
  public static void main(String[] args) {
    TimeOutTable<Object,Object> timeOutTable = new TimeOutTable<Object,Object>(5);

    timeOutTable.put("Ivan", "BMW");

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
    }

    timeOutTable.put("Ivan",123);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    System.out.println("--------------");
    timeOutTable.remove("Ivan");

    System.out.println();
  }
}
