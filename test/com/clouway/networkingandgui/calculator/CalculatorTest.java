package com.clouway.networkingandgui.calculator;

import com.clouway.networkingandgui.calculator.core.Display;
import com.clouway.networkingandgui.calculator.core.TheCalculator;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CalculatorTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  TheCalculator calculator;
  Display display = context.mock(Display.class);

  @Before
  public void init() {
    Map<String, String> operations = new HashMap<String, String>() {{
      put("+", "+");
      put("-", "-");
      put("/", "/");

    }};
    calculator = new TheCalculator(display, operations);
  }

  @Test
  public void buttonWasPressed() throws Exception {
    context.checking(new Expectations() {{
      oneOf(display).displayText("5");
    }});

    calculator.onNumberPressed("5");

  }

  @Test
  public void pressAnotherButton() throws Exception {
    context.checking(new Expectations() {{
      oneOf(display).displayText("12");
    }});

    calculator.onNumberPressed("12");
  }

  @Test
  public void happyPath() throws Exception {
    context.checking(new Expectations() {{
      oneOf(display).displayText("5");
      oneOf(display).displayText("+");
      oneOf(display).displayText("3");

      // equal 8
      oneOf(display).displayText("8");

    }});

    calculator.onNumberPressed("5");
    calculator.onOperationPressed("+");
    calculator.onNumberPressed("3");
    calculator.onEvaluate();
  }

  @Test
  public void wrongOperationIsProvided() throws Exception {

    context.checking(new Expectations() {{
      never(display);
    }});

    calculator.onOperationPressed("4");

  }
}