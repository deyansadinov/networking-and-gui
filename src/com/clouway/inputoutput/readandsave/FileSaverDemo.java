package com.clouway.inputoutput.readandsave;


import java.io.IOException;
import java.util.Scanner;

public class FileSaverDemo {
  public static void main(String[] args) {
    System.out.println("First enter a breaking simbol or something.\nEnter the name of the file .\nAnd then the text :");

    Scanner scan = new Scanner(System.in);

    String breakPoint = scan.nextLine();
    String fileName = scan.nextLine();

    FileSaver sf = new FileSaver(fileName,breakPoint);

    try {
      sf.writeToFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    scan.close();
  }
}
