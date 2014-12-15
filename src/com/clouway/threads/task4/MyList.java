package com.clouway.threads.task4;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:13 Nov 14-11-3
 */
// Да се направи така, че достъпът до методите на класа да е синхронизиран.
// Да се направят две отделни нишки за методите put и remove така, че при едновременно извикване на няколко такива нишки да не възникнат проблеми:
//
//       - При викане на remove при вече празен списък, не трябва да възникне грешка, а да се чака да се въведе елемент;
//       - При викане на put при пълен списък не трябва да възникне грешка, а да се чака да се махне елемент.
//       - Да се ползва wait() и notify()

public class MyList {

  private int index = 0;
  private Integer content;
  Integer[] myList = new Integer[3];


  public synchronized void add(Integer value) {
    while (myList[myList.length - 1] != null) {
      try {
        System.out.println("Waiting to put...");
        wait();
      } catch (InterruptedException e) {
      }
    }
    content = value;
    myList[index]=content;
    if(index+1>myList.length-1){
      index=myList.length-1;

    }else{
      index++;
    }


    notifyAll();
  }

  public synchronized void removeLastElement() {
    while (myList[index]==null) {
      try {
        System.out.println("Waiting to remove.....");
        wait();
      } catch (InterruptedException e) {
        System.out.println("interupted");
      }
    }

    System.out.println(Thread.currentThread().getName() + " hase removed the element "+ myList[index]);
    content = null;
    myList[index] =content;
    if(index-1<0){
      index=0;
    }else{
      index--;
    }

    notifyAll();

  }
}
