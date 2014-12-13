package com.clouway.gui.calculator.calculation;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by clouway on 14-11-21.
 */
public class RPN implements CalculationAlgorithm {

  @Override
  public double calculate(String expression) {
    String postfixNotation = toPostfix(expression);
    return evaluate(postfixNotation);
  }

  private String toPostfix(String infixNotation) {
    StringBuilder postfixNotation = new StringBuilder();
    Stack<String> operatorStack = new Stack<String>();
    String equRegex = "\\d+\\.\\d+|\\d+|" + Operators.getRegex();

    Pattern equationPattern = Pattern.compile(equRegex);
    Matcher equationMatcher = equationPattern.matcher(infixNotation);

    while (equationMatcher.find()) {
      if (equationMatcher.group().length() != 0) {
        String element = equationMatcher.group().trim();
        if (isOperation(element)) {
          pushInStackOrAddToPostfix(element, operatorStack, postfixNotation);
        } else {
          postfixNotation.append(element);
          postfixNotation.append(',');
        }
      }
    }
    if (!operatorStack.isEmpty()) {
      dumpStack(postfixNotation, operatorStack);
    }
    return postfixNotation.toString();
  }

  public double evaluate(String postfixNotation) {
    String[] elements = postfixNotation.split(",");
    Stack<String> bufferedNotation = new Stack<String>();
    for (String each : elements) {
      if (isOperation(each)) {
        String operandTwo = bufferedNotation.pop();
        String operandOne = bufferedNotation.pop();
        String result = performOperation(each, operandOne, operandTwo);
        bufferedNotation.push(result);
      } else {
        bufferedNotation.add(each);
      }
    }

    double result = Double.parseDouble(bufferedNotation.peek());

    return result;
//    return Math.round(result);
  }

  private void dumpStack(StringBuilder postfixNotation, Stack<String> operations) {
    while (!operations.empty()) {
      postfixNotation.append(operations.pop());
      postfixNotation.append(',');
    }
  }

  private void pushInStackOrAddToPostfix(String operation, Stack<String> operatorStack, StringBuilder postfixNotation) {
    if (canPushInOperatorStack(operation, operatorStack)) {
      operatorStack.push(operation);
    } else {
      postfixNotation.append(operatorStack.pop());
      postfixNotation.append(',');
      pushInStackOrAddToPostfix(operation, operatorStack, postfixNotation);
    }
  }

  private boolean canPushInOperatorStack(String operation, Stack<String> operatorStack) {
    if (operatorStack.isEmpty()) {
      return true;
    }
    String top = operatorStack.peek();
    if (("*".equals(operation) || "/".equals(operation)) && ("+".equals(top) || "-".equals(top))) {
      return true;
    }
    if ("^".equals(operation) && (("*".equals(top) || "/".equals(top) ||
            "+".equals(top) || "-".equals(top)))) {
      return true;
    }
    return false;
  }

  private boolean isOperation(String symbol) {
    boolean isOperation = String.valueOf(symbol).matches(Operators.getRegex());
//    boolean isOperation = String.valueOf(symbol).matches("[+-/*^]");
    return isOperation;
  }


  private String performOperation(String operation, String fstOperand, String sndOperand)
          throws IllegalArgumentException {

    double op1 = Double.parseDouble(fstOperand);
    double op2 = Double.parseDouble(sndOperand);

    for (Operators each : Operators.values()) {
      if (each.toString().equals(operation)) {
        return Double.toString(each.calculate(op1, op2));
      }
    }
    throw new IllegalArgumentException("Unknown operand");
  }
}
