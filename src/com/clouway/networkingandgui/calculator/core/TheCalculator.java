package com.clouway.networkingandgui.calculator.core;

import java.util.Map;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:34 Nov 14-11-26
 */
public class TheCalculator implements TheCalculatorListener {
  private Map<String, String> operations;

  private Integer left = null;
  private Integer right = null;


  private Display display;

  public TheCalculator(Display display, Map<String, String> operations) {
    this.display = display;
    this.operations = operations;
  }


  @Override
  public void onNumberPressed(String number) {

    display.displayText(number);
    if (left == null) {
      left = Integer.valueOf(number);
      return;

    }
    if (right == null) {
      right = Integer.valueOf(number);
      return;
    }


  }

  @Override
  public void onOperationPressed(String operation) {

    if (!operations.containsKey(operation)) {
       return;
    }

    display.displayText(operation);
  }

  @Override
  public void onEvaluate() {
    int result = left + right;
    display.displayText("" + result);
  }
}
