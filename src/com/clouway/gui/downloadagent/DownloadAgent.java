package com.clouway.gui.downloadagent;

import com.clouway.gui.downloadagent.downloader.URLAddressException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by clouway on 14-12-2.
 */
public class DownloadAgent {
  private final ProgressUpdateListener updateListener;
  private long fileSize;

  public DownloadAgent(ProgressUpdateListener updateListener) {
    this.updateListener = updateListener;
  }

  public void download(String urlAddress, String fileName) throws URLAddressException, FileNotFoundException {

    try {
      InputStream inputStream = getInputStreamForURLAddress(urlAddress);
      OutputStream outputStream = getFileOutputStream(fileName);
      transfer(inputStream, outputStream, 0, -1);
    } catch (MalformedURLException e) {
      throw new URLAddressException(urlAddress);
    } catch (IOException e) {
      throw new FileNotFoundException(e.getMessage());
    }

  }

  private InputStream getInputStreamForURLAddress(String urlAddress) throws MalformedURLException, FileNotFoundException {
    try {
      URLConnection urlConnection = new URL(urlAddress).openConnection();
      fileSize = urlConnection.getContentLengthLong();
      InputStream inputStream = urlConnection.getInputStream();
      return inputStream;
    } catch (MalformedURLException e) {
      throw e;
    } catch (IOException e) {
      throw new FileNotFoundException(e.getMessage());
    }
  }

  private OutputStream getFileOutputStream(String name) throws FileNotFoundException {
    try {
      OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(name)));
      return outputStream;
    } catch (FileNotFoundException e) {
      throw e;
    }
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
  private long transfer(InputStream inStream, OutputStream outStream, int offset, int numberOfBytes) throws IOException {

    BufferedInputStream bufIn = new BufferedInputStream(inStream);

    // If the user enters a negative offset don't do anything
    if (offset < 0) {
      return 0;
    }

    // Skip the desired number of bytes
    bufIn.skip(offset);

    byte[] buffer = new byte[1024]; // The byte[] that will copy in segments of byte.length bytes
//    byte[] buffer = new byte[102400]; // The byte[] that will copy in segments of byte.length bytes

    // If the number of bytes entered by the user are more than the available copy the file appropriately
    if (numberOfBytes < buffer.length && numberOfBytes != -1) {
      bufIn.read(buffer, 0, numberOfBytes);
      outStream.write(buffer, 0, numberOfBytes);
      outStream.flush();

      return numberOfBytes;
    }

    long transferred = 0; // Keep track of the bytes that are transferred

    while (transferred < numberOfBytes || numberOfBytes == -1) {
      int readBytes;

      if ((readBytes = bufIn.read(buffer, 0, buffer.length)) != -1) {
        outStream.write(buffer, 0, readBytes);
        transferred += readBytes;
        updateListener.onUpdate(percentProcessed(transferred));
        if (isInterrupted()) {
          break;
        }
        outStream.flush();
      } else break;

    }
    return transferred;
  }

  private int percentProcessed(long transferred) {
    return (int) (100 * transferred / fileSize);
  }

  private boolean isInterrupted() {
    try {
      Thread.sleep(0);
    } catch (InterruptedException e) {
      return true;
    }
    return false;
  }


}
