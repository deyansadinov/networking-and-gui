package com.clouway.calculatortdd;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public interface CalculatorListener {
  void onDigitClicked(String digit);

  void onOperationClicked(String operation);
  void calculate();
}
