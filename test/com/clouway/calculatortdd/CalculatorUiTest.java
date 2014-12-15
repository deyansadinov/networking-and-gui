package com.clouway.calculatortdd;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class CalculatorUiTest {
  @Mock
  private CalculatorListener calculatorListener;
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void happyPath() {
    CalculatorUi calculatorUi = new CalculatorUi();
    calculatorUi.showUi(calculatorListener);
    context.checking(new Expectations() {{
      oneOf(calculatorListener).onDigitClicked("2");
    }});
    calculatorUi.onDigitClicked("2");
  }

  @Test
  public void anotherOnClickButton() {
    CalculatorUi calculatorUi = new CalculatorUi();
    calculatorUi.showUi(calculatorListener);

    context.checking(new Expectations() {{
      oneOf(calculatorListener).onDigitClicked("4");
    }});
    calculatorUi.onDigitClicked("4");
  }


}
