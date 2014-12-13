package com.clouway.gui.calculator;

import com.clouway.gui.calculator.calculation.Operators;

/**
 * Created by clouway on 14-12-1.
 */
public class EquationBuilder {

  private StringBuilder equation = new StringBuilder();
  private String lastSymbol = "";
  private boolean canEnterDot = true;

  public EquationBuilder append(String pendingSymbol) {

    if (isOperation(pendingSymbol)) {
      insertOperation(pendingSymbol);
    } else if (isDot(pendingSymbol)) {
      insertDot(pendingSymbol);
    } else {
      equation.append(pendingSymbol);
      lastSymbol = pendingSymbol;
    }
    return this;
  }

  public void clear() {
    equation = new StringBuilder();
    lastSymbol = "";
    canEnterDot = true;
  }

  public String content() {
    return equation.toString();
  }

  public String build() {
    if (isDot(lastSymbol) || isOperation(lastSymbol)) {
      return "";
    }
    return equation.toString();
  }

  private void insertDot(String pendingSymbol) {
    if (equation.length() == 0) {
      return;
    }
    if (isDot(lastSymbol)) {
      return;
    }
    if (canEnterDot) {
      equation.append(pendingSymbol);
      lastSymbol = pendingSymbol;
      canEnterDot = false;
    }
  }


  private void insertOperation(String pendingSymbol) {
    if (equation.length() == 0) {
      return;
    }

    if (isOperation(lastSymbol) || isDot(lastSymbol)) {
      equation.deleteCharAt(equation.length() - 1);
    }
    canEnterDot = true;
    equation.append(pendingSymbol);
    lastSymbol = pendingSymbol;
  }

  private boolean isOperation(String symbol) {
    if (isDot(symbol)) {
      return false;
    }
    boolean isOperation = String.valueOf(symbol).matches(Operators.getRegex());
    return isOperation;
  }

  private boolean isDot(String symbol) {
    if (".".equals(symbol)) {
      return true;
    }
    return false;
  }

}
