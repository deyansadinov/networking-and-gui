package com.clouway.exceptions.console;

/**
 * Created by clouway on 14-9-16.
 */
public class NumberOutOfBoundsException extends Exception {

  public String toString() {
    return "The number must be between 0 and 100 .";
  }

}
