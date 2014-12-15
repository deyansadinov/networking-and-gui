package com.clouway.exceptions.sumator;

public class Sumator {
  /**
   * @param a-string
   * @param b-string
   * @return-integer
   */
  public int sum(String a, String b) {

    try {
      return Integer.parseInt(a) + Integer.parseInt(b);
    } catch (NumberFormatException e) {
      throw  new SumErrorException();
    }

  }
}
