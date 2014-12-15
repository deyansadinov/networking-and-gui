package com.clouway.threads.task2;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:35 Nov 14-11-6
 */
public class CountingThread extends Thread {

  private int timer;
  private boolean active = true;
  private CountingThread secondCounter;

  public CountingThread(int timer) {

    this.timer = timer;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setTarget(CountingThread secondCounter) {
    this.secondCounter = secondCounter;
  }


  public void setTimer(int timer) {
    this.timer = timer;
  }

  public void run() {
    while (active) {
      for (int i = 0; i <= timer; i++) {
        System.out.println(Thread.currentThread().getName() + " " + i);
        if (i==timer){
          secondCounter.setTimer(timer);
          secondCounter.setActive(false);
        }
          try {
            sleep(1000);
          } catch (InterruptedException e) {
          }

      }
     setActive(false);

    }

  }
}
