package com.clouway.threads.task5;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:30 Nov 14-11-10
 */
public class Main {
  public static void main(String[] args) {

    TimeOutTable timeOutTable = new TimeOutTable(5);

    timeOutTable.put("Ivan", "BMW");

    try {
      Thread.sleep(3000);
      System.out.println("---------------");
    } catch (InterruptedException e) {
    }

   timeOutTable.put("Ivan", "VW");
    timeOutTable.put("Ivan", "BMW");

    try {
      Thread.sleep(3000);
      System.out.println("---------------");
    } catch (InterruptedException e) {
    }

    timeOutTable.put("Ivan", "VW");

    //timeOutTable.get("Ivan");
//    try {
//      Thread.sleep(10000);
//    } catch (InterruptedException e) {}
//    System.out.println();

  }
}
