package com.clouway.inputoutput.console;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DataReaderDemo {
  public static void main(String[] args) throws FileNotFoundException {

    InputStream in = DataReaderDemo.class.getResourceAsStream("sample.txt.txt");
    System.out.println();
    DataReader fileReader = new DataReader(in);

    System.out.println("Read from console return Int :");
    int num = fileReader.readInt();
    System.out.println(num);

    System.out.println("Read from console return char :");
    char ch = fileReader.readChar();
    System.out.println(ch);

    System.out.println("Read from console return float :");
    float fl = fileReader.reaAFloat();
    System.out.println(fl);

    System.out.println("Read from console return string :");
    String text = fileReader.readString();
    System.out.println(text);
  }
}
