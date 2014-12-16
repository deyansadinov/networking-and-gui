package downloadagent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class TransferObject {

  private final DownloadProgressListener downloadProgressListener;
  private final int contentLength;
  private int byteCounter = 0;
  private byte[] buff = new byte[2048];
  private int readBytes;
  private int availableBytes = 0;

  public TransferObject(DownloadProgressListener downloadProgressListener, int contentLength) {
    this.downloadProgressListener = downloadProgressListener;
    this.contentLength = contentLength;
  }

  public int transfer(InputStream in, OutputStream out, int numberOfBytes, int offset) throws IOException {


    if (offset < 0 && numberOfBytes < -1) {
      throw new IllegalArgumentException();
    }

    in.skip(offset);
    if (numberOfBytes == -1) {
      while ((readBytes = in.read(buff)) != -1) {
        out.write(buff, 0, readBytes);
        byteCounter += readBytes;
      }
      return byteCounter;
    }
    if (buff.length >= numberOfBytes) {
      in.read(buff, 0, numberOfBytes);
      out.write(buff, 0, numberOfBytes);
      return numberOfBytes;
    }
    while ((readBytes = in.read(buff)) != -1) {
      if (numberOfBytes > 0 && ((readBytes = in.read(buff, 0, buff.length)) != -1)) {
        byteCounter += readBytes;
        out.write(buff, 0, readBytes);
      }
      if (readBytes + byteCounter > numberOfBytes) {
        availableBytes = numberOfBytes - byteCounter;
        byteCounter += availableBytes;
      }
      in.read(buff, 0, readBytes);
      out.write(buff, 0, availableBytes);
      if (numberOfBytes == byteCounter) {
        return numberOfBytes;
      }
    }
    return numberOfBytes;
  }

}
