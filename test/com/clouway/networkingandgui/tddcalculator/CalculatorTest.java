package com.clouway.networkingandgui.tddcalculator;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;

public class CalculatorTest {
  @Rule
  public JUnitRuleMockery mockery = new JUnitRuleMockery();
  final Display display = mockery.mock(Display.class);

  @Test
  public void oneNumberPressed() throws Exception {

    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});

    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
    }});
    calculator.numberIsPressed("10");

  }

  @Test
  public void anotherNumberPressed() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("15");
    }});
    calculator.numberIsPressed("15");
  }

  @Test
  public void operatorAfterNumber() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("1");
      oneOf(display).displayText("1+");
    }});
    calculator.numberIsPressed("1");
    calculator.operatorIsPressed("+");
  }
//TO DO
  @Test
  public void  expressionFromTwoNumbersAndOneOperator() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10+");
      oneOf(display).displayText("10+10");

    }});
    calculator.numberIsPressed("10");
    calculator.operatorIsPressed("+");
    calculator.numberIsPressed("10");

  }

  @Test
  public void operatorAfterOperator() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10+");
    }});

    calculator.numberIsPressed("10");
    calculator.operatorIsPressed("+");
    calculator.operatorIsPressed("+");

  }

  @Test
  public void operatorAfterDiferentOperator() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
      put("+", "+");
      put("-", "-");
      put("/", "/");
      put("*", "*");
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("1");
      oneOf(display).displayText("1-");
      never(display).displayText("+");

    }});
    calculator.numberIsPressed("1");
    calculator.operatorIsPressed("-");
    calculator.operatorIsPressed("+");

  }

  @Test
  public void expressionFromTwoNumbersAndTwoOperators() throws Exception {

    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10+");
      oneOf(display).displayText("10+10");
      oneOf(display).displayText("10+10+");

    }});
    calculator.numberIsPressed("10");
    calculator.operatorIsPressed("+");
    calculator.numberIsPressed("10");
    calculator.operatorIsPressed("+");

  }

  @Test
  public void putOnlyOperator() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      never(display).displayText("+");
    }});
    calculator.operatorIsPressed("+");

  }

  @Test
  public void clear() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("");

    }});
    calculator.numberIsPressed("10");
    calculator.clearIsPressed("C");
  }

  @Test
  public void removeAftherOperator() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("22");
      oneOf(display).displayText("22+");

      oneOf(display).displayText("22");

    }});
    calculator.numberIsPressed("22");
    calculator.operatorIsPressed("+");
    calculator.removeIsPressed("R");
  }

  @Test
  public void expressionStartingWithDot() throws Exception {

    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      never(display).displayText(".");

    }});
    calculator.dotIsPressed(".");
  }

  @Test
  public void typeDotAfterDigit() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10.");

    }});
    calculator.numberIsPressed("10");
    calculator.dotIsPressed(".");
  }

  @Test
  public void dotAfterDot() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10.");
    }});

    calculator.numberIsPressed("10");
    calculator.dotIsPressed(".");
    calculator.dotIsPressed(".");
  }

  @Test
  public void digitDotDigitDot() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10.");
      oneOf(display).displayText("10.14");
      never(display).displayText("10.14.");
    }});
    calculator.numberIsPressed("10");
    calculator.dotIsPressed(".");
    calculator.numberIsPressed("14");
    calculator.dotIsPressed(".");

  }

  @Test
  public void operatorAfterDot() throws Exception {

    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
      put("+", "+");
      put("-", "-");
      put("/", "/");
      put("*", "*");

    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10.");
      never(display).displayText("10.+");
    }});
    calculator.numberIsPressed("10");
    calculator.dotIsPressed(".");
    calculator.operatorIsPressed("+");
  }

  @Test
  public void expressionWithTwoDots() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
      put("+", "+");
      put("-", "-");
      put("/", "/");
      put("*", "*");

    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10.");
      oneOf(display).displayText("10.15");
      oneOf(display).displayText("10.15+");
      oneOf(display).displayText("10.15+13");
      oneOf(display).displayText("10.15+13.");
      oneOf(display).displayText("10.15+13.45");

    }});
    calculator.numberIsPressed("10");
    calculator.dotIsPressed(".");
    calculator.numberIsPressed("15");
    calculator.operatorIsPressed("+");
    calculator.numberIsPressed("13");
    calculator.dotIsPressed(".");
    calculator.numberIsPressed("45");
  }

  @Test
  public void dotAfterOperator() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
      put("+", "+");
      put("-", "-");
      put("/", "/");
      put("*", "*");

    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10+");
      oneOf(display).displayText("10+");
    }});
    calculator.numberIsPressed("10");
    calculator.operatorIsPressed("+");
    calculator.dotIsPressed(".");

  }

  @Test
  public void sum_simpleValues() throws Exception {
    Calculator calculator = new Calculator(display, new HashMap<String, String>() {{
      put("+", "+");
      put("-", "-");
      put("/", "/");
      put("*", "*");

    }});
    mockery.checking(new Expectations() {{
      oneOf(display).displayText("10");
      oneOf(display).displayText("10+");
      oneOf(display).displayText("10+5");
      oneOf(display).displayText("15.0");
    }});
    calculator.numberIsPressed("10");
    calculator.operatorIsPressed("+");
    calculator.numberIsPressed("5");
    calculator.eqIsPressed("=");
  }

}