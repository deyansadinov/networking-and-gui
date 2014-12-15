package com.clouway.inputoutput.reversefile;


import java.io.IOException;

public class FileReverserDemo {
  public static void main(String[] args) throws IOException {

    FileReverser reverseFile = new FileReverser();
    reverseFile.reverse("text.txt");

  }
}
