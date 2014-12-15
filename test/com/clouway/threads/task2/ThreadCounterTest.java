package com.clouway.threads.task2;

import org.junit.Test;

import java.util.Scanner;

public class ThreadCounterTest {
  @Test
  public void testName() throws Exception {
    Thread thread=new Thread(new com.clouway.threads.task1.ThreadCounter(10));
    thread.start();

    while (thread.isAlive()){

      Scanner scanner=new Scanner(System.in);
      String text="";

      text+=scanner.next();
      if(!text.equals("")){
        thread.interrupt();

      }
    }

  }
}