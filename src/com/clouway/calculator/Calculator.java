package com.clouway.calculator;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class Calculator {
  private final String toCalculate;
  private List<String> digits = Lists.newArrayList();

  public Calculator(String toCalculate) {
    this.toCalculate = toCalculate;
  }

  public Double calculate() {
    List<String> numberList = convertContentToList();
    return calculate(numberList);
  }

  private double calculate(List<String> numberList) {
    int i = 0;
    double result = 0;
    while (numberList.size() != 1) {
      i++;
      String currentElement = numberList.get(i);
      if (!isDigit(currentElement)) {
        boolean isListContainHighPriority = numberList.contains("/") || numberList.contains("*");
        if (!isHighPriority(currentElement) && isListContainHighPriority) {
          swapElementIfIsNotHighPriority(i, numberList.get(i + 2));
          currentElement = numberList.get(i);
        }
        char operation = currentElement.charAt(0);
        double previous = parseToDouble(numberList.get(i - 1));
        double next = parseToDouble(numberList.get(i + 1));

        switch (operation) {
          case '+':
            result = previous + next;
            break;
          case '-':
            result = previous - next;
            break;
          case '/':
            result = previous / next;
            break;
          case '*':
            result = previous * next;
            break;
        }
        numberList.subList(0, 3).clear();
        numberList.add(0, String.valueOf(result));
        i = 0;
      }
    }
    return result;
  }

  private void swapElementIfIsNotHighPriority(Integer currentIndex, String nextSign) {
    if (!isHighPriority(digits.get(currentIndex)) && isHighPriority(nextSign)) {
      digits.add(digits.get(currentIndex));
      digits.add(digits.get(currentIndex - 1));
      digits.subList(0, 2).clear();
    }
  }

  private List<String> convertContentToList() {
    Pattern pattern = Pattern.compile("[-,\\+,\\/,*]|[0-9]*\\.?[0-9]+");
    Matcher m = pattern.matcher(toCalculate);
    while (m.find()) {
      if (digitContainMoreThanOneFloatingPoint(m.group())) {
        throw new FloatingPointOverflowException();
      }
      digits.add(m.group());
    }
    System.out.println(digits);
    return digits;
  }

  private boolean digitContainMoreThanOneFloatingPoint(String content) {
    return content.indexOf(".") == 0;
  }

  private boolean isDigit(String content) {
    try {
      Double.parseDouble(content);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private boolean isHighPriority(String sign) {
    return sign.equals("/") || sign.equals("*");
  }

  private Double parseToDouble(String stringToConvert) {
    return Double.parseDouble(stringToConvert);
  }
}
