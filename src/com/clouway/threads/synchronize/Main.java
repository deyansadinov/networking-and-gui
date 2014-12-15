package com.clouway.threads.synchronize;

import com.clouway.threads.task5.ThreadedAdd;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:43 Nov 14-11-13
 */
public class Main {
  public static void main(String[] args) {
    final int[] num = {0};


   Thread a= new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i <100; i++) {
          num[0]++;
          System.out.println("From a "+num[0]);

        }



      }
    });

    Thread b= new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          System.out.println("Dasdasd");
          System.out.println(Thread.currentThread().getName()+" -- "+num[0]);

        }
        }
    });

    a.setPriority(10);
    b.setPriority(1);

    a.start();
    b.start();
  }
}
