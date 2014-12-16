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

  private DownloadAgentUI downloadAgentUI;
  private ProgressListener progressListener;

  private int downloadedBytes;

  private long fileSize;

  private int byteCounter = 0;
  private byte[] buff = new byte[1024];
  private int readBytes;
  private int availableBytes = 0;
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
//     connection.connect();



      InputStream inputStream = address.openStream();
      InputStreamReader input = new InputStreamReader(inputStream);
      reader = new BufferedReader(input);

      OutputStream outputStream = new FileOutputStream(filePath);
      writer = new OutputStreamWriter(outputStream);

      fileSize = connection.getContentLengthLong();
      downloadedBytes = transfer(inputStream, outputStream, -1, 0);

//        transfer(inputStream, outputStream, -1, 0);


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
//    }
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
        if ((readBytes = in.read(buff, 0, buff.length)) != -1) {
          out.write(buff, 0, readBytes);
          System.out.println("percents : " + calculatePercentTransferred(downloadedBytes));
          progressListener.onProgressWasUpdate(calculatePercentTransferred(downloadedBytes));
          downloadedBytes += readBytes;

          out.flush();
        } else {
          System.out.println("percents : " + calculatePercentTransferred(downloadedBytes));
          progressListener.onProgressWasUpdate(calculatePercentTransferred(downloadedBytes));
          interrupted = true;
          in.close();
          notify();
          break;
        }
      }
      break;
    }


//    if (numberOfBytes == -1) {
//      while ((readBytes = in.read(buff)) != -1) {
//        out.write(buff, 0, readBytes);
//        byteCounter += readBytes;
//
//        System.out.println(byteCounter);
//        progressListener.onProgressWasUpdate(calculatePercentTransferred(byteCounter));
////        sleepAWhile();
//        try {
//          Thread.sleep(500);
//        } catch (InterruptedException e) {
//          break;
//        }
//      }
////      return byteCounter;
//    }
//
////    if (buff.length >= numberOfBytes) {
////      in.read(buff, 0, numberOfBytes);
////      out.write(buff, 0, numberOfBytes);
////      return numberOfBytes;
////    }
////
////    while ((readBytes = in.read(buff)) != -1) {
////      if (numberOfBytes > 0 && ((readBytes = in.read(buff, 0, buff.length)) != -1)) {
////        byteCounter += readBytes;
////        out.write(buff, 0, readBytes);
////      }
////      if (readBytes + byteCounter > numberOfBytes) {
////        availableBytes = numberOfBytes - byteCounter;
////        byteCounter += availableBytes;
////      }
////      in.read(buff, 0, readBytes);
////      out.write(buff, 0, availableBytes);
////      if (numberOfBytes == byteCounter) {
////        return numberOfBytes;
////      }
////    }
////    return numberOfBytes;
    return downloadedBytes;
  }




  private int calculatePercentTransferred(int bytesTransferred) {
    return (int) (100 * bytesTransferred / fileSize);
  }




//  private int transffer(InputStream inputStream, OutputStream outputStream, int numberOfBytes, int offset) throws IOException {
//
//    BufferedInputStream inputBuff = new BufferedInputStream(inputStream);
//
//    if (offset < 0 && numberOfBytes < -1) {
//      throw new IllegalArgumentException();
//    }
//
//    inputBuff.skip(offset);
//    byte[] buff = new byte[1024];
//
//    if (numberOfBytes < buff.length && numberOfBytes != -1) {
//      inputBuff.read(buff, 0, numberOfBytes);
//      outputStream.write(buff, 0, numberOfBytes);
//      return numberOfBytes;
//    }
//
//    while (transferredBytes < numberOfBytes || numberOfBytes == -1) {
//      if ((readBytes = inputBuff.read(buff, 0, buff.length)) != -1) {
//        outputStream.write(buff, 0, readBytes);
//        transferredBytes += readBytes;
//      } else {
//        inputStream.close();
//      }
//    }
//    return transferredBytes;
//  }
}
