package com.clouway.collection.pagebean.taskone;

import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:44 Oct 14-9-19
 */

public class PageBeanDemo {
  public static void main(String[] args) {
    List<Integer> elements = new ArrayList<>();

    elements.add(1);
    elements.add(2);
    elements.add(3);
    elements.add(4);
    elements.add(5);
    elements.add(6);
    elements.add(7);
    elements.add(8);
    elements.add(9);
    elements.add(10);
    
    PageBean pb = new PageBean(elements, 3);

    System.out.println( pb.next().toString());
    System.out.println( pb.next().toString());
    System.out.println( pb.next().toString());
    System.out.println( pb.previous().toString());




    System.out.printf("The current page  is %s .", pb.getCurrentPageNumber());
    System.out.printf("Is there a next element - %s .", pb.hasNext());
    System.out.printf("Is there a previous element - %s .", pb.hasPrevious());
    System.out.println();


    pb.firstPage();

    System.out.println();
    System.out.printf("The curent page  is %s .", pb.getCurrentPageNumber());
    System.out.printf("Is there a next element - %s .", pb.hasNext());
    System.out.printf("Is there a previous element - %s .", pb.hasPrevious());


        pb.lastPage();

    System.out.println();
    System.out.printf("The curent page  is %s .", pb.getCurrentPageNumber());
    System.out.printf("Is there a next element - %s .", pb.hasNext());
    System.out.printf("Is there a previous element - %s .", pb.hasPrevious());



//
//    /**
//     * Starts the program.To be able to use the console with next() and prev() .
//     */
//    public void start() {
//      while (true) {
//        if (programStart) {
//          next();
//          programStart = false;
//        }
//        if ((scanner.next()).equals("next()")) {
//          next();
//        } else {
//          if ((scanner.next()).equals("prev()")) {
//            previous();
//          }
//        }
//
//      }
//    }
  }
}
