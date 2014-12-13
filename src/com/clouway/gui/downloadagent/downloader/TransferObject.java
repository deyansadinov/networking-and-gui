package com.clouway.gui.downloadagent.downloader;

import com.clouway.gui.downloadagent.ProgressUpdateListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by clouway on 14-9-17.
 */
public class TransferObject {


  private final ProgressUpdateListener updateListener;

  public TransferObject(ProgressUpdateListener updateListener) {
    this.updateListener = updateListener;
  }

  /**
   * Transfer segment of bytes from a InputStream to a OutputStream
   *
   * @param inStream      is the stream to read from.
   * @param outStream     if the stream to write to.
   * @param offset        the number of bytes to be skipped.
   * @param numberOfBytes the number of bytes needed to be copied.
   *                      <li>If the number of bytes is more than the bytes
   *                      available the entire InputStream is copied.
   *                      <li>If -1 is entered the enitre InputStream is copied.
   * @return the number of bytes transferred.
   * @throws java.io.IOException
   */
  public int transfer(InputStream inStream, OutputStream outStream, int offset, int numberOfBytes) throws IOException {

    BufferedInputStream bufIn = new BufferedInputStream(inStream);

    // If the user enters a negative offset don't do anything
    if (offset < 0) {
      return 0;
    }

    // Skip the desired number of bytes
    bufIn.skip(offset);

    byte[] buffer = new byte[1024]; // The byte[] that will copy in segments of byte.length bytes

    // If the number of bytes entered by the user are more than the available copy the file appropriately
    if (numberOfBytes < buffer.length && numberOfBytes != -1) {
      bufIn.read(buffer, 0, numberOfBytes);
      outStream.write(buffer, 0, numberOfBytes);
      outStream.flush();
      return numberOfBytes;
    }

    int transferred = 0; // Keep track of the bytes that are transferred

    while (transferred < numberOfBytes || numberOfBytes == -1) {
      int readBytes;

      if ((readBytes = bufIn.read(buffer, 0, buffer.length)) != -1) {
        outStream.write(buffer, 0, readBytes);
        transferred += readBytes;
        System.out.println(transferred);
        updateListener.onUpdate(transferred);
        sleepAWhile();
        outStream.flush();
      } else break;

    }
    return transferred;
  }

  private void sleepAWhile() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.exit(0);
    }
  }

}
