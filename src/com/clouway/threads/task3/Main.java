package com.clouway.threads.task3;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:50 Oct 14-10-30
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {

    ThreadCount threadCount=new ThreadCount();
    CounterTwo counterTwo =new CounterTwo(threadCount,5);
    CounterOne counterOne =new CounterOne(threadCount,5);

    counterTwo.setName("CounterTwo: ");
    counterOne.setName("CounterOne : " );



    counterTwo.start();
    counterOne.start();


  }
}
