package com.clouway.networkingandgui.downloadagent.agent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 12:32 Nov 14-11-29
 */
public class NewDownloadAgent {

  private int offset = 0;
  private int numberOfBytes = -1;
  private int transferred = 0;
  private int readBytes;
  private PListener pListener;
  private int maxValue = 0;
  private boolean stop=false;

  public NewDownloadAgent(PListener pListener) {

    this.pListener = pListener;
  }

  public void setStop(boolean stop) {
    this.stop = stop;
  }

  public void setTransferred(int transferred) {
    this.transferred = transferred;
  }

  public int getTransferred() {

    pListener.onProgressUpdate(transferred);
    return transferred;
  }

  public  int downloadFile(String urlName, String fileName) {
    if (urlName.equals("") || fileName.equals("")) {
      return 0;
    }
    try {
      URL url = new File(urlName).toURI().toURL();
      URLConnection connection = url.openConnection();
      InputStream in = connection.getInputStream();
      BufferedInputStream bufIn = new BufferedInputStream(in);
      OutputStream out = new FileOutputStream(fileName + "." + getExtention(urlName));
      pListener.setMax(connection.getContentLength());
      if (offset < 0) {
        return 0;
      }

      bufIn.skip(offset);
      byte[] buffer = new byte[5024];

      if (numberOfBytes < buffer.length && numberOfBytes != -1) {
        bufIn.read(buffer, 0, numberOfBytes);
        out.write(buffer, 0, numberOfBytes);
        out.flush();
        return numberOfBytes;
      }
      while (!stop){
      while (transferred < numberOfBytes || numberOfBytes == -1) {


          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
          if ((readBytes = bufIn.read(buffer, 0, buffer.length)) != -1) {
            System.out.println("--" + transferred);
            pListener.onProgressUpdate(transferred);
            out.write(buffer, 0, readBytes);
            transferred += readBytes;

             out.flush();
          } else {
            stop=true;

            in.close();
            break;
          }

        }

      }
      return transferred;

    } catch (IOException e) {
      throw new InvalidURLException();
    }
  }

  private String getExtention(String urlName) {
    String[] ext = urlName.split("[.]");
    return ext[ext.length - 1];
  }

}
