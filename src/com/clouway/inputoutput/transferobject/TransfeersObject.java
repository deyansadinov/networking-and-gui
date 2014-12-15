package com.clouway.inputoutput.transferobject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransfeersObject {

  public int transfer(InputStream in, OutputStream out, int numberOfBytes, int offset) throws IOException {

    BufferedInputStream buf = new BufferedInputStream(in);

    byte[] buffer = new byte[25];
    int lenght = buffer.length;
    int index = 0;

    buf.skip(offset);

    if (numberOfBytes < -1) {
      numberOfBytes = 0;
    }
    if (numberOfBytes < lenght && numberOfBytes!=-1) {
      lenght = numberOfBytes;
    }
    if (numberOfBytes == -1) {
      numberOfBytes = buf.available();
    }

    while (buf.read(buffer, 0, lenght) != -1) {
      System.out.println(index);
      out.write(buffer, 0, lenght);
      index += lenght;
      if ((index + lenght) > numberOfBytes) {
        lenght = numberOfBytes - index;
      }
      if (index == numberOfBytes) {
        return index;
      }
    }
    return index;
  }
}
