package com.clouway.threads.producerconsumer.normal;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:56 Oct 14-10-31
 */
public class Consumer extends Thread{
  private Holle holle;
  private int number;

  public Consumer(Holle c,int number){

    holle = c;
    this.number = number;
  }
  public void run(){
    int value=0;

    for(int i=0;i<=5;i++){
      value=holle.get();
      System.out.println("Consumer # "+this.number+" got :"+value);
    }
  }
}
