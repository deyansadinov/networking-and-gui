package test4;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgent {

  private ProgessListener progessListener;
  private int transferred = 0;
  private int fileSize = 0;
  private boolean stop=false;

  public void setStop(boolean stop) {
    this.stop = stop;
  }

//  public boolean isStop() {
//    return stopMessage;
//  }

  public void setTransferred(int transferred) {
    this.transferred = transferred;
  }

  public DownloadAgent(ProgessListener progessListener) {

    this.progessListener = progessListener;
  }

  public int startDownload(String urlName, String fileName) {
    if (urlName.equals("") || fileName.equals("")) {
      return 0;
    }
    try {
      URL url = new URI(urlName).toURL();
      URLConnection connection = url.openConnection();

      if (connection.getContentLength() == 0) {
        return 0;
      }
      fileSize = connection.getContentLength();
      OutputStream out = new FileOutputStream(fileName);
      transfer(connection.getInputStream(), out, -1, 0);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return transferred;
  }

  public synchronized int transfer(InputStream inStream, OutputStream outStream, int numberOfBytes, int offset) throws IOException {

    BufferedInputStream bufIn = new BufferedInputStream(inStream);

    if (offset < 0) {
      return 0;
    }

    bufIn.skip(offset);

    byte[] buffer = new byte[1024];

    if (numberOfBytes < buffer.length && numberOfBytes != -1) {
      bufIn.read(buffer, 0, numberOfBytes);
      outStream.write(buffer, 0, numberOfBytes);
      outStream.flush();
      return numberOfBytes;
    }

    while (transferred < numberOfBytes || numberOfBytes == -1) {
      while (!stop){
        int readBytes;
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
         break;
        }

        System.out.println(transferred);
        if ((readBytes = bufIn.read(buffer, 0, buffer.length)) != -1) {
          outStream.write(buffer, 0, readBytes);
          progessListener.onProgressUpdate(transferred * 100 / fileSize);
          transferred += readBytes;
          outStream.flush();
        } else {
          progessListener.onProgressUpdate(transferred * 100 / fileSize);
          stop = true;
          inStream.close();
          notifyAll();
          break;
        }
      }
      break;
    }
    return transferred;
  }
}
