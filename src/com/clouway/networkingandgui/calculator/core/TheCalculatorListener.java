package com.clouway.networkingandgui.calculator.core;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:32 Nov 14-11-26
 */
public interface TheCalculatorListener {

  void onNumberPressed(String number);

  void onOperationPressed(String operation);

  void onEvaluate();
}
