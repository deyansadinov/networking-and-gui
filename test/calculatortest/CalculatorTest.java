package calculatortest;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import test2.Calculator;
import test2.CalculatorView;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class CalculatorTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  CalculatorView view;


  @Test
  public void addingNumberToCalculatorDisplay() {
    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).getExpression();
      oneOf(view).setExpression("6");
      will(returnValue("6"));
    }});

    String actualExpression = calculator.numberPressed('6');

    assertThat(actualExpression,is("6"));
  }


  @Test
  public void addingOperationAfterNumber() {

    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).getExpression();
      will(returnValue("3"));
      oneOf(view).setExpression("3+");
      will(returnValue("3+"));
    }});

    String actualExpression = calculator.operationPressed('+');

    assertThat(actualExpression,is("3+"));
  }

  @Test
  public void addingOperationAfterOperation() {

    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).getExpression();
      will(returnValue("4"));
      oneOf(view).setExpression("4+");
      oneOf(view).getExpression();
      will(returnValue("4+"));
    }});

    calculator.operationPressed('+');
    String actualExpression = calculator.operationPressed('+');

    assertThat(actualExpression,is("4+"));
  }

  @Test
  public void backspace() {

    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).getExpression();
      will(returnValue("3+9"));
      oneOf(view).setExpression("3+");
      will(returnValue("3+"));
    }});

    String actualExpression = calculator.backspacePressed();

    assertThat(actualExpression,is("3+"));
  }

  @Test
  public void addingTwoPointInNumber() {

    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).getExpression();
      will(returnValue("6"));
      oneOf(view).setExpression("6" + ".");
      will(returnValue("6."));
      oneOf(view).getExpression();
      will(returnValue("6."));

    }});

    calculator.pointPressed();
    String actualExpression = calculator.pointPressed();

    assertThat(actualExpression,is("6."));
  }
  
  @Test
  public void deletedExpression() {

    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).setExpression("");
      will(returnValue(""));
    }});

    String actualExpression = calculator.deletePressed();

    assertThat(actualExpression,is(""));
  }

}
