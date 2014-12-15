package com.clouway.threads.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:26 Nov 14-11-4
 */
public class Main {
  public static void main(String[] args) {
    BlockingQueue blockingQueue = new LinkedBlockingDeque();

    Thread produc=new Thread(new Produc(blockingQueue));
    Thread cl=new Thread(new Cons(blockingQueue));

cl.setPriority(10);
    produc.setPriority(1);
    produc.start();
    cl.start();
  }
}
