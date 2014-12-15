package com.clouway.inputoutput.reversefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReverser {
  /**
   * Reverse the text from a file
   *
   * @param fileName
   * @throws IOException
   */
  public void reverse(String fileName) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    StringBuilder reversed = new StringBuilder();

    String text;

    while ((text = reader.readLine()) != null) {
      reversed.append(text).append("\n");
    }

    saveToFile(fileName, reversed.reverse().toString());
    reader.close();
  }
  /**
   * Saves to a file.
   *
   * @param fileName
   * @param text
   * @throws IOException
   */
  private void saveToFile(String fileName, String text){
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(fileName));
      writer.write(text);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
