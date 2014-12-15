package com.clouway.networkingandgui.tddcalculator;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:23 Nov 14-11-26
 */
public interface CalculatorListener {

  void numberIsPressed(String text);

  void operatorIsPressed(String text);

  void clearIsPressed(String text);

  String getText();

  void removeIsPressed(String text);

  void dotIsPressed(String text);

  void eqIsPressed(String text);
}
