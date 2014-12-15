package com.clouway.networkingandgui.downloadagent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:58 Nov 14-11-24
 */
public class DownloadingThread extends Thread {

  private TransfeersObject transfeersObject;
  private final InputStream in;
  private final FileOutputStream outputStream;

  public DownloadingThread(TransfeersObject transfeersObject, InputStream in, FileOutputStream outputStream) {

    this.transfeersObject = transfeersObject;
    this.in = in;
    this.outputStream = outputStream;
  }

  public void run() {
    try {
      transfeersObject.transfer(in, outputStream, -1, 0);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
