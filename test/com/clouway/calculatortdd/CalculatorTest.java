package com.clouway.calculatortdd;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class CalculatorTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private Display display;

  Calculator calculator;

  @Before
  public void init() {
    calculator = new Calculator(display);
  }

  @Test
  public void happyPath() {
    showText("4");

    calculator.onDigitClicked("4");
  }

  @Test
  public void summingTwoNumbers() {
    showText("6+3", "9");
    onButtonClickCalculator(calculator, "6+3");
    calculator.calculate();
  }

  @Test
  public void summingAnotherTwoNumbers() {
    showText("4+7", "11");
    onButtonClickCalculator(calculator, "4+7");
    calculator.calculate();
  }

  @Test
  public void summingTwoNumbersWithFloatingPoint() {
    showText("2.1+5.2", "7.3");

    onButtonClickCalculator(calculator, "2.1+5.2");
    calculator.calculate();
  }

  @Test
  public void subtractOfTwoNumbers() {
    showText("2.1-1.0", "1.1");

    onButtonClickCalculator(calculator, "2.1-1.0");
    calculator.calculate();
  }

  @Test
  public void divisionOfTwoNumbers() {
    showText("9.0/4", "2.25");

    onButtonClickCalculator(calculator, "9.0/4");
    calculator.calculate();
  }

  @Test
  public void multiplyOfTwoNumbers() {
    showText("3*2", "6");

    onButtonClickCalculator(calculator, "3*2");
    calculator.calculate();
  }

  @Test
  public void calculateWithMoreNumbers() {
    showText("3*2+1", "7");

    onButtonClickCalculator(calculator, "3*2+1");
    calculator.calculate();
  }

  @Test
  public void calculateWithDifferentPriorities() {
    showText("4+3*2", "10");

    onButtonClickCalculator(calculator, "4+3*2");
    calculator.calculate();
  }

  private void onButtonClickCalculator(Calculator calculator, final String... numbers) {
    for (String number : numbers) {
      if (!canCastToDigit(number)) {
        calculator.onOperationClicked(number);
        continue;
      }
      calculator.onDigitClicked(number);
    }
  }

  private void showText(final String... numbers) {
    for (final String number : numbers) {
      context.checking(new Expectations() {{
        oneOf(display).showText(number);
      }});
    }
  }

  private boolean canCastToDigit(String text) {
    try {
      Double.parseDouble(text);
      return true;
    } catch (NumberFormatException be) {
      return false;
    }
  }
}
