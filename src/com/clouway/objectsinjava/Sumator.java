package com.clouway.objectsinjava;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Sumator {
  /**
   * @param a-Integer
   * @param b-Integer
   * @return-Integer
   */
  public int sum(int a, int b) {
    return a + b;
  }

  /**
   * @param a-bouble
   * @param b-bouble
   * @return-bouble
   */
  public double sum(double a, double b) {
    return a + b;
  }

  /**
   * @param a-string
   * @param b-string
   * @return-integer
   */
  public int sum(String a, String b) {
    return Integer.parseInt(a) + Integer.parseInt(b);
  }

  /**
   * @param a-BigInteger
   * @param b-BigInteger
   * @return - int
   */
  public BigInteger sum(BigInteger a, BigInteger b) {
    return a.add(b);
  }

  /**
   * @param a-BigDecimal
   * @param b-BigDecimal
   * @return-BigBDecimmal
   */
  public BigDecimal sum(BigDecimal a, BigDecimal b) {
    return a.add(b);
  }
}
