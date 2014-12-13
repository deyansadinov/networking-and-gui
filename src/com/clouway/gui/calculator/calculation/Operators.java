package com.clouway.gui.calculator.calculation;

/**
 * Created by clouway on 14-11-19.
 */
public enum Operators {

  PLUS("+") {
    @Override
    double calculate(double fstOperand, double sndOperand) {
      double result = fstOperand + sndOperand;
      return result;
    }
  },

  MINUS("-") {
    @Override
    double calculate(double fstOperand, double sndOperand) {
      double result = fstOperand - sndOperand;
      return result;
    }
  },

  DIVIDE("/") {
    @Override
    double calculate(double fstOperand, double sndOperand) {
      double result = fstOperand / sndOperand;
      return result;
    }
  },

  MULTIPLY("*") {
    @Override
    double calculate(double fstOperand, double sndOperand) {
      double result = fstOperand * sndOperand;
      return result;
    }
  },

  POWER_OF("^") {
    @Override
    double calculate(double fstOperand, double sndOperand) {
      return Math.pow(fstOperand, sndOperand);
    }
  };


  private final String symbol;

  Operators(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }

  public static String getRegex() {
    StringBuilder operatorRegex = new StringBuilder("[");
    for (Operators each : Operators.values()) {
      operatorRegex.append(each.toString());
    }
    operatorRegex.append("]");
    return operatorRegex.toString();
  }

  abstract double calculate(double fstOperand, double sndOperand);


}
