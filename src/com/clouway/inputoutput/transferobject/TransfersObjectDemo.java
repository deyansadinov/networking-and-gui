package com.clouway.inputoutput.transferobject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransfersObjectDemo {
  public static void main(String[] args) {



    try {
      FileInputStream in = new FileInputStream("aaaa.jpg");
      FileOutputStream out = new FileOutputStream("new.png");
      TransfeersObject tr = new TransfeersObject();

      int numberOfBytes=-1;
      int num=tr.transfer(in, out,numberOfBytes,0);
      System.out.printf("%s bytes transfered.",num);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }


  }
}
