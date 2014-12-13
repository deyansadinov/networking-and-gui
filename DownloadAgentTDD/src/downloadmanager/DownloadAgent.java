package downloadmanager;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 14-12-3.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class DownloadAgent implements Downloader{


  private Display downloadWindow;


  private ErrorListener errorHandler;
  private int totalDownloadedBytes = 0;


  public DownloadAgent(Display downloadWindow, ErrorListener errorHandler) {

    this.downloadWindow = downloadWindow;
    this.errorHandler=errorHandler;

  }

  @Override
  public int processDownloading(String URLAddress, String fileName) {

    try {

      if (!errorHandler.catchEmptyURLorFileField() && !errorHandler.catchUnexistingURLaddres()) {
        
        URL url = new URL(URLAddress);
        int fileLenht = url.openConnection().getContentLength();

        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(fileName);


        byte[] buffer = new byte[20000];
        int readBytes = 0;

        while ((readBytes = is.read(buffer)) > 0) {

          os.write(buffer, 0, readBytes);
          totalDownloadedBytes += readBytes;

          downloadWindow.udateProgresBar(totalDownloadedBytes * 100 / fileLenht);

          System.out.println(totalDownloadedBytes * 100 / fileLenht);

          Thread.sleep(200);

        }
        is.close();
        os.close();

      }

    } catch (InterruptedException ie) {
      return 0;
    } catch (IOException e) {
      return 0;
    }
    return totalDownloadedBytes;
  }

}

