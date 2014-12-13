package com.clouway.gui.calculator;

import com.clouway.gui.calculator.calculation.Operators;

/**
 * Created by clouway on 14-12-1.
 */
public class EquationBuilder {

  private StringBuilder equation = new StringBuilder();
  private String lastSymbol = "";
  private boolean canEnterDot = true;

  /**
   * Appends a symbol if to the equation if the symbol can be appended at all.
   *
   * @param pendingSymbol is the desired symbol to be appended.
   * @return this instance of the EquationBuilder.
   */
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

  /**
   * Deletes the existing equation and guarantees you a fresh start.
   */
  public void clear() {
    equation = new StringBuilder();
    lastSymbol = "";
    canEnterDot = true;
  }

  /**
   * Gets the current content of the equation. It might not be a correct equation
   * For example it might return: 1+2*
   *
   * @return the current content of the equation
   */
  public String content() {
    return equation.toString();
  }

  /**
   * Checks if the content of the equation is correct and returns it. If not it returns a empty string "".
   *
   * @return a correct equation or empty string "" if equation not complete.
   */
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
