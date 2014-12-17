package download;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;


/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgent {

  private ProgressListener progressListener;
  private int downloadedBytes;
  private long fileSize;
  private byte[] buff = new byte[1024];
  private boolean interrupted = false;

  public void isInterrupted(boolean interrupted) {
    this.interrupted = interrupted;
  }

  public DownloadAgent(ProgressListener progressListener) {
    this.progressListener = progressListener;
  }

  public int downloadStart(String filePath, String pageAddress) throws URISyntaxException {

    BufferedReader reader = null;
    OutputStreamWriter writer = null;

    try {
      if (pageAddress.equals("") || filePath.equals("")) {
        return 0;
      }

      URL address = new URI(pageAddress).toURL();
      URLConnection connection = address.openConnection();

      InputStream inputStream = address.openStream();
      InputStreamReader input = new InputStreamReader(inputStream);
      reader = new BufferedReader(input);

      OutputStream outputStream = new FileOutputStream(filePath);
      writer = new OutputStreamWriter(outputStream);

      fileSize = connection.getContentLengthLong();
      downloadedBytes = transfer(inputStream, outputStream, -1, 0);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return downloadedBytes;
  }

  public synchronized int transfer(InputStream in, OutputStream out, int numberOfBytes, int offset) throws IOException {

    BufferedInputStream buffIn = new BufferedInputStream(in);

    if (offset < 0 && numberOfBytes < -1) {
      throw new IllegalArgumentException();
    }

    in.skip(offset);

    if (numberOfBytes < buff.length && numberOfBytes != -1) {
      buffIn.read(buff, 0, numberOfBytes);
      out.write(buff, 0, numberOfBytes);
      out.flush();
      return numberOfBytes;
    }

    while (downloadedBytes < numberOfBytes || numberOfBytes == -1) {
      while (!interrupted) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          break;
        }

        System.out.println(downloadedBytes);
        int readBytes;
        if ((readBytes = in.read(buff, 0, buff.length)) != -1) {
          out.write(buff, 0, readBytes);
          progressListener.onProgressWasUpdate(calculatePercentTransferred(downloadedBytes));
          downloadedBytes += readBytes;
          out.flush();
        } else {
          progressListener.onProgressWasUpdate(calculatePercentTransferred(downloadedBytes));
          interrupted = true;
          in.close();
          notify();
          break;
        }
      }
      break;
    }
    return downloadedBytes;
  }

  private int calculatePercentTransferred(int bytesTransferred) {
    return (int) (100 * bytesTransferred / fileSize);
  }

  public void cancelDownload() {
    this.isInterrupted(true);
  }
}
