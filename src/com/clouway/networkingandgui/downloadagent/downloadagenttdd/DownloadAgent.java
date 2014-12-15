package com.clouway.networkingandgui.downloadagent.downloadagenttdd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:21 Nov 14-11-27
 */
public class DownloadAgent implements DownloadListener {

  private ProgressListener listener;
  private int transferred = 0;
  private int readBytes;
  private int downloadedBytes = 0;
  private final int offset = 0;
  private final int numberOfBytes = -1;

  public DownloadAgent(ProgressListener listener) {

    this.listener = listener;
  }

  @Override
  public int downloadStart(String forUrlText, String forDownloadFileText) {
    if (forUrlText.equals("") || forDownloadFileText.equals("")) {
      return 0;
    }

    try {
      URL url = new File(forUrlText).toURI().toURL();
      URLConnection connection = url.openConnection();
      InputStream in = connection.getInputStream();
      OutputStream out = new FileOutputStream(forDownloadFileText + "." + getFileExtension(url.getPath()));

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

        System.out.println(transferred);
        listener.onProgressUpdate(transferred);

        try {
          Thread.sleep(100);

          // System.out.println(transferred);
        } catch (InterruptedException e) {
        }
        if ((readBytes = bufIn.read(buffer, 0, buffer.length)) != -1) {


          out.write(buffer, 0, readBytes);
          transferred += readBytes;
          listener.onProgressUpdate(transferred);

          out.flush();
        } else {
          in.close();
          break;
        }
      }
      return transferred;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return transferred;
  }

  public String getFileExtension(String txt) {
    String[] ext = txt.split("[.]");
    return ext[ext.length - 1];
  }


}
