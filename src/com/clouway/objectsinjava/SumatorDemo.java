package com.clouway.objectsinjava;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SumatorDemo {
  public static void main(String[] args) {
    Sumator s = new Sumator();

    int num = s.sum(10, 2);
    System.out.println("Method addNewProductForUse(a,b) returns integer :" + num);

    double dnum = s.sum(2.3, 4.5);
    System.out.println("Method addNewProductForUse(a,b) returns double  :" + dnum);

    int strToint = s.sum("3", "8");
    System.out.println("Method addNewProductForUse(a,b) returns integer from string  :" + strToint);

    BigInteger bi = s.sum(new BigInteger("1234"), new BigInteger("123455678"));
    System.out.println("Method addNewProductForUse(a,b) returns integer from BigInteger  :" + bi);

    BigDecimal bd = s.sum(new BigDecimal("1.4"), new BigDecimal("1.245555555555555555"));
    System.out.println("Method addNewProductForUse(a,b) returns integer from BigDecimal  :" + bd);
  }
}
