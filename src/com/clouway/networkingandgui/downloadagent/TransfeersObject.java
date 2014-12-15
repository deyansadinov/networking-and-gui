package com.clouway.networkingandgui.downloadagent;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransfeersObject {

  private int transferred = 0;
  private int readBytes;
  private boolean stop = false;

  public int getTransferred() {
    return transferred;
  }

  public void setStop(boolean stop) {
    this.stop = stop;
  }

  public void setTransferred(int transferred) {
    this.transferred = transferred;
  }

  public int transfer(InputStream in, OutputStream out, int numberOfBytes, int offset) throws IOException {

    BufferedInputStream bufIn = new BufferedInputStream(in);

    if (offset < 0) {
      return 0;
    }

    bufIn.skip(offset);
    byte[] buffer = new byte[1024];

    if (numberOfBytes < buffer.length && numberOfBytes != -1) {
      bufIn.read(buffer, 0, numberOfBytes);
      out.write(buffer, 0, numberOfBytes);
      out.flush();
      return numberOfBytes;
    }
    while (transferred < numberOfBytes || numberOfBytes == -1) {
      while (!stop) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if ((readBytes = bufIn.read(buffer, 0, buffer.length)) != -1) {

          System.out.println(transferred);
          out.write(buffer, 0, readBytes);
          transferred += readBytes;
          out.flush();
        } else {
          stop = true;
          in.close();
          notifyAll();
          break;
        }
      }

    }
    return transferred;
  }


}
