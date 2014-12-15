package com.clouway.threads.task1;

import java.util.Scanner;

import static java.lang.Thread.interrupted;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:14 Nov 14-11-3
 */
public class Asd {


  public static void main(String[] args) {
    Thread thread = new Thread(new Runnable() {

      private boolean stop = false;

      @Override
      public void run() {
        int counter = 0;
        while (!stop) {
          counter += 1;
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            System.out.println(counter);
            stop = true;

          }
        }

      }
    });

    thread.start();

    Scanner scanner = new Scanner(System.in);
    scanner.next();

    thread.interrupt();


  }
}
