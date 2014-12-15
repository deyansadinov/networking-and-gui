package com.clouway.collection.pagebean.mesagemanager;

import java.util.Map;

public class ExceptionsMessageManager {

  private final Map<String, String> exceptions;
  private StringBuilder txt = new StringBuilder();

  public ExceptionsMessageManager(Map<String, String> exceptions) {
    this.exceptions = exceptions;
  }
  /**
   * @param messageCode-key
   * @param message
   */
  public void registerErrorMessage(String messageCode, String message) {

    if (exceptions.containsValue(message)) {
      throw new MessageExistsException();
    }
    exceptions.put(messageCode, message);
  }
  /**
   * @param messageCode
   * @return
   */
  public String raiseError(String messageCode) {
    if (exceptions.containsKey(messageCode)) {
      txt.append(exceptions.get(messageCode)).append("\n");
    } else {
      throw new KeyIsNotFreeException();
    }
    return txt.toString();
  }
  public java.util.Collection<String> getErrorMessages() {
    return exceptions.values();
  }
}
