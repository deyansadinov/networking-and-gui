package com.clouway.threads.producerconsumer.normal;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:56 Oct 14-10-31
 */
public class Main {
  public static void main(String[] args) throws InterruptedException {
    Holle holle = new Holle();

    Producer producer = new Producer(holle, 1);
    Consumer consumer = new Consumer(holle, 1);

    producer.setPriority(10);
    consumer.setPriority(1);


    producer.start();
    consumer.start();

  }
}
