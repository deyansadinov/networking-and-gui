package com.clouway.downloadagent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class DownloadAgent {
  private DownloadHandler downloadHandler;
  private final String url;
  private String filename;
  private boolean isCancelClicked = false;

  public DownloadAgent(DownloadHandler downloadHandler, String url, String filename) {
    this.downloadHandler = downloadHandler;
    this.url = url;
    this.filename = filename;
  }

  public void update(Double percent) {
    downloadHandler.update(percent.intValue());
  }

  public synchronized void download() {
    if (url.isEmpty()) {
      throw new EmptyUrlException();
    }

    try {
      URI uri = new URI(url);
      URL url;
      try {
        url = uri.toURL();

      } catch (IllegalArgumentException e) {
        throw new IncorrectUrlException();
      }
      URLConnection urlConnection = url.openConnection();
      int size = urlConnection.getContentLength();

      BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename));
      int count;
      byte data[] = new byte[1024];
      Double sumCount = 0d;

      while ((count = in.read(data, 0, 1024)) != -1) {
        if (isCancelClicked) {
          File file = new File(filename);
          file.delete();
          return;
        }
        out.write(data, 0, count);
        sumCount += count;
        update(sumCount / size * 100.0);

      }
      in.close();
      out.close();
    } catch (URISyntaxException e) {
      throw new WrongUrlException();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void cancel() {
    isCancelClicked = true;
  }
}
