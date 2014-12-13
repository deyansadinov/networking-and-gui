package com.clouway.gui.downloadagent.downloader;

/**
 * Created by laptop on 14-11-29.
 */
public class URLAddressException extends RuntimeException {
  public URLAddressException(String address) {
    super(String.format("Incorrect URL Address: '%s'",address));
  }
}
