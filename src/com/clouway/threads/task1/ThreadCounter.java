package com.clouway.threads.task1;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:42 Oct 14-10-28
 */
//
//Да се направи програма, в която се стартира нишка отброяваща до зададена стойност.
//
//        След стартирането на нишката, в главната прорама да се чака въвеждането на символ от клавиатурата.
//        След прочитането на символа да се спира нишката и да се извежда до коя стойност е стигнало отброяването.

public class ThreadCounter implements Runnable {

  private final int timer;

  public ThreadCounter(int countTo) {
    this.timer = countTo;
  }

  @Override
  public void run() {
    for (int i = timer; i >=0; i--) {
      try {
        Thread.sleep(1000);

      } catch (InterruptedException e) {
        System.out.println("It took you "+(timer -i) + " seconds to enter something.");
        return;
      }
      System.out.println("Time : "+i);
    }
  }
}
