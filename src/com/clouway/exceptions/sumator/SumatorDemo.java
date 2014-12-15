package com.clouway.exceptions.sumator;


public class SumatorDemo {
  public static void main(String[] args) {

    Sumator calculator = new Sumator();
    try{
      System.out.println(calculator.sum("12a", "2"));
    }catch (SumErrorException e){
      System.out.println("Enter only numbers.");
    }



  }
}
