package com.clouway.inputoutput.console;

import java.io.InputStream;
import java.util.*;

import java.util.Scanner;

public class DataReader {

  private Scanner scanner;

  public DataReader(InputStream in) {
    this.scanner = new Scanner(in);
  }

  public int readInt() throws InputMismatchException {
    int num = scanner.nextInt();
    scanner.nextLine();
    return num;
  }

  public char readChar() {
    return  scanner.nextLine().charAt(0);

  }

  public float reaAFloat() {
    float fl = scanner.nextFloat();
    scanner.nextLine();
    return fl;
  }

  public String readString() {
    return scanner.nextLine();

  }

}
