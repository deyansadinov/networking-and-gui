package com.clouway.todelete;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class ToDelete implements Runnable {

  private String url;
  private String filename;

  public ToDelete(String url, String filename) {
    this.url = url;
    this.filename = filename;
  }

  @Override
  public void run() {
    try {
      URI uri = new URI(url);
      URL url = uri.toURL();
      URLConnection urlConnection = url.openConnection();

      BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename));

      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream digestInputStream = new DigestInputStream(in, sha);

      int bytes;
      while ((bytes = digestInputStream.read()) != -1) {
        out.write(bytes);
      }
      in.close();
      out.close();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
