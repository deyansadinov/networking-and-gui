package com.clouway.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class CalculatorTest {
  @Rule
  public ExpectedException exception = ExpectedException.none();


  @Test
  public void happyPath() {
    String toCalculate = "2+4";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(6.0));
  }

  @Test
  public void subtract() {
    String toCalculate = "3+4-1";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(6.0));
  }

  @Test
  public void subtractWithFirstMinusSign() {
    String toCalculate = "2-4";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(-2.0));
  }

  @Test
  public void subtractWithPolyDigit() {
    String toCalculate = "47-43";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(4.0));
  }

  @Test
  public void subtractWithFloatingPointDigit() {
    String toCalculate = "47.0-43";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(4.0));
  }

  @Test
  public void division() {
    String toCalculate = "6/3";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(2.0));
  }

  @Test
  public void divisionWithFloatingPoint() {
    String toCalculate = "6.2/3";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(2.066666666666667));
  }

  @Test
  public void divisionWithFloatingPointAndSummation() {
    String toCalculate = "3+6.2/5";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(4.24));
  }

  @Test
  public void anotherDivisionWithFloatingPointAndSummation() {
    String toCalculate = "4+2/3/1+5/4";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(5.916666666666667));
  }

  @Test
  public void multiplication() {
    String toCalculate = "4*8";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(32.0));
  }

  @Test
  public void multiplicationWithSummation() {
    String toCalculate = "1+4.2*8+2";
    Calculator calculator = new Calculator(toCalculate);
    assertThat(calculator.calculate(), is(36.6));
  }

  @Test
  public void addMoreFloatingPointToDigit() {
    exception.expect(FloatingPointOverflowException.class);
    String toCalculate = "54766.77.5554";
    Calculator calculator = new Calculator(toCalculate);
    calculator.calculate();
  }

}
