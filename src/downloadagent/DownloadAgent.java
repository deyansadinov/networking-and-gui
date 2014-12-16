package downloadagent;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class DownloadAgent {

  private DownloadProgressListener downloadProgressListener;
  private TransferObject downloadObject;

  public void downloadProgressListener(DownloadProgressListener downloadProgressListener) {
    this.downloadProgressListener = downloadProgressListener;
  }

  public void downloadFile(String filePath, String pageAddress) {
    BufferedReader reader = null;
    OutputStreamWriter writer = null;

    try {
      URL address = new URL(pageAddress);
      URLConnection connection = address.openConnection();
      connection.connect();

      InputStream inputStream = connection.getInputStream();
      InputStreamReader input = new InputStreamReader(inputStream);
      reader = new BufferedReader(input);

      OutputStream outputStream = new FileOutputStream(filePath);
      writer = new OutputStreamWriter(outputStream);

      this.downloadObject = new TransferObject(downloadProgressListener,connection.getContentLength());
      downloadObject.transfer(inputStream,outputStream,-1,0);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null){
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (writer != null){
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
