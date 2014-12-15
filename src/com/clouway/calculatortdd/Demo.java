package com.clouway.calculatortdd;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Demo {
  public static void main(String[] args) {
    CalculatorUi calculatorUi = new CalculatorUi();
    Calculator calculator = new Calculator(calculatorUi);
    calculatorUi.showUi(calculator);
  }
}
