package calculator;

import org.jmock.Expectations;
;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tddcalculator.Calculator;
import tddcalculator.Display;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class CalculatorTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  Calculator calculator;
  final Display display = context.mock(Display.class);

  @Before
  public void setUp() throws Exception {
    final Map<String, String> operations = new HashMap<String, String>();
    operations.put("+", "+");
    operations.put("-", "-");
    operations.put("*", "*");
    operations.put("/", "/");


    calculator = new Calculator(display, operations);

  }
  @Test
  public void wrongOperation() {
    calculator.onOperationPressed("sg");
  }

  @Test
  public void buttonWasPressed() {

    context.checking(new Expectations() {{

      oneOf(display).text("5");

    }});

    calculator.onNumberPressed(5);
  }

  @Test
  public void pressAnotherButton() {

    context.checking(new Expectations() {{

      oneOf(display).text("8");
    }});

    calculator.onNumberPressed(8);
  }

  @Test
  public void pressTwoNumbers() {
    context.checking(new Expectations() {{

      oneOf(display).text("8");
      oneOf(display).text("84");
    }});

    calculator.onNumberPressed(8);
    calculator.onNumberPressed(4);

  }

  @Test
  public void operatorAfterNumber() {
    context.checking(new Expectations() {{
      oneOf(display).text("10");
      oneOf(display).text("10+");
    }});
    calculator.onNumberPressed(10);
    calculator.onOperationPressed("+");

  }

  @Test
  public void operatorAfterOperator() {
    context.checking(new Expectations() {{
      oneOf(display).text("10");
      oneOf(display).text("10+");
      never(display).text("-");
    }});
    calculator.onNumberPressed(10);
    calculator.onOperationPressed("+");
    calculator.onOperationPressed("-");
  }

  @Test
  public void expressionStartWithOperator() {
    context.checking(new Expectations() {{
      never(display).text("+");
    }});

  }

  @Test
  public void expressionStartWithPoint() {
    context.checking(new Expectations() {{
      never(display).text(".");
    }});

    calculator.onPointPressed();
  }


  @Test
  public void numberAfterPoint() {

    context.checking(new Expectations() {{
      oneOf(display).text("2");
      oneOf(display).text("2.");
      oneOf(display).text("2.3");
    }});

    calculator.onNumberPressed(2);
    calculator.onPointPressed();
    calculator.onNumberPressed(3);
  }

  @Test
  public void backspace() {
    context.checking(new Expectations() {{
      oneOf(display).text("2");
      oneOf(display).text("23");
      oneOf(display).text("2");
      oneOf(display).text("24");
      oneOf(display).text("2");
    }});

    calculator.onNumberPressed(2);
    calculator.onNumberPressed(3);
    calculator.onBackspacePressed();
    calculator.onNumberPressed(4);
    calculator.onBackspacePressed();
  }

  @Test
  public void calculateExpression() {
    context.checking(new Expectations() {{
      oneOf(display).text("10");
      oneOf(display).text("10+");
      oneOf(display).text("10+3");
      oneOf(display).text("13.0");

    }});

    calculator.onNumberPressed(10);
    calculator.onOperationPressed("+");
    calculator.onNumberPressed(3);
    calculator.onEvaluate();
  }

  @Test
  public void pointAfterOperator() {
    context.checking(new Expectations() {{
      oneOf(display).text("10");
      oneOf(display).text("10+");
      never(display).text("10+.");
    }});
    calculator.onNumberPressed(10);
    calculator.onOperationPressed("+");
    calculator.onPointPressed();
  }

  @Test
  public void operationAfterPoint(){

      context.checking(new Expectations(){{
        oneOf(display).text("10");
        oneOf(display).text("10.");
        never(display).text("10.+");
      }});

    calculator.onNumberPressed(10);
    calculator.onPointPressed();
    calculator.onOperationPressed("+");

  }

  @Test
  public void calculateExpressionWithTwoPointsInIt() {

    context.checking(new Expectations(){{
      oneOf(display).text("1");
      oneOf(display).text("1.");
      oneOf(display).text("1.2");
      oneOf(display).text("1.2+");
      oneOf(display).text("1.2+2");
      oneOf(display).text("1.2+2.");
      oneOf(display).text("1.2+2.8");
      oneOf(display).text("4.0");
    }});

    calculator.onNumberPressed(1);
    calculator.onPointPressed();
    calculator.onNumberPressed(2);
    calculator.onOperationPressed("+");
    calculator.onNumberPressed(2);
    calculator.onPointPressed();
    calculator.onNumberPressed(8);
    calculator.onEvaluate();
  }

  @Test
  public void twoPointsInOneNumber() {

    context.checking(new Expectations(){{
      oneOf(display).text("10");
      oneOf(display).text("10.");
      oneOf(display).text("10.3");
      never(display).text("10.3.");
    }});

    calculator.onNumberPressed(10);
    calculator.onPointPressed();
    calculator.onNumberPressed(3);
    calculator.onPointPressed();
  }

  @Test
  public void pointAfterPoint() {

    context.checking(new Expectations(){{
      oneOf(display).text("10");
      oneOf(display).text("10.");
      never(display).text("10..");
    }});

    calculator.onNumberPressed(10);
    calculator.onPointPressed();
    calculator.onPointPressed();
  }

}
