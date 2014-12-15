package com.clouway.threads.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:26 Oct 14-10-29
 */
public class Main {
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    Thread thread = new Thread(new ThreadCounter(1000));
    thread.start();

    while (thread.isAlive()) {
      String text = "";
      text += scanner.next();
      if (!text.equals("")) {
        thread.interrupt();

      }
    }


    scanner.close();
  }
}
