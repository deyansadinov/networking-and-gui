package com.clouway.calculatortdd;

import com.clouway.calculator.FloatingPointOverflowException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Calculator implements CalculatorListener {
  private final Display display;
  private List<String> numberList = new ArrayList<String>();
  private Character operation;
  private StringBuilder toCalculate = new StringBuilder();

  public Calculator(Display display) {
    this.display = display;
  }

  @Override
  public void onDigitClicked(String text) {
    toCalculate.append(text);
    display.showText(toCalculate.toString());
  }

  @Override
  public void onOperationClicked(String operation) {
    toCalculate.append(operation);
    this.operation = operation.charAt(0);
    display.showText(toCalculate.toString());
  }

  @Override
  public void calculate() {
    numberList = convertContentToList(toCalculate.toString());
    StringBuilder result = new StringBuilder();
    BigDecimal tempResult = null;

    for (int index = 0; index < numberList.size(); index++) {
      String current = numberList.get(index);
      if (!isParsableToDigit(current)) {

        if (!isHighPriority(current) && isListContainHighPriority()) {
          swapElementsWithHighPriority(index);
          current = numberList.get(index);
        }
        String previous = elementOf(index - 1);
        String next = elementOf(index + 1);
        char operation = current.charAt(0);
        switch (operation) {
          case '+':
            tempResult = new BigDecimal(previous).add(new BigDecimal(next));
            break;
          case '-':
            tempResult = new BigDecimal(previous).subtract(new BigDecimal(next));
            break;
          case '/':
            tempResult = new BigDecimal(previous).divide(new BigDecimal(next));
            break;
          case '*':
            tempResult = new BigDecimal(previous).multiply(new BigDecimal(next));
            break;
        }
        removeFirstThreeElements(index);

        numberList.add(0, tempResult.toString());
        index = 0;
        result.append(tempResult);
      }
    }

    display.showText(numberList.get(0));
  }

  private boolean isListContainHighPriority() {
    return numberList.contains("*") || numberList.contains("/");
  }

  private void swapElementsWithHighPriority(int index) {
    String current = numberList.get(index);
    String previous = numberList.get(index - 1);
    String next = numberList.get(index + 2);

    if (isHighPriority(next)) {

      numberList.add(current);
      numberList.add(previous);
      numberList.subList(0, 2).clear();
    }
  }

  private List<String> convertContentToList(String expression) {
    List<String> numberList = new ArrayList<String>();
    Pattern pattern = Pattern.compile("[-,\\+,\\/,*]|[0-9]*\\.?[0-9]+");
    Matcher m = pattern.matcher(expression);
    while (m.find()) {
      if (digitContainMoreThanOneFloatingPoint(m.group())) {
        throw new FloatingPointOverflowException();
      }
      numberList.add(m.group());
    }
    return numberList;
  }

  private boolean digitContainMoreThanOneFloatingPoint(String content) {
    return content.indexOf(".") == 0;
  }

  private boolean isHighPriority(String sign) {
    return sign.equals("/") || sign.equals("*");
  }

  private void removeFirstThreeElements(int index) {
    numberList.remove(index - 1);
    numberList.remove(index - 1);
    numberList.remove(index - 1);
  }

  private String elementOf(int index) {
    return numberList.get(index);
  }

  private boolean isParsableToDigit(String text) {
    try {
      Double.parseDouble(text);
      return true;
    } catch (NumberFormatException be) {
      return false;
    }
  }
}
