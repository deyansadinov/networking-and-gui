package calculator;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;


public class CalculatorTest {

  @Rule
  public JUnitRuleMockery context =  new JUnitRuleMockery();
  private Display display=context.mock(Display.class);
  private MathProcessor processor = context.mock(MathProcessor.class);
  private Calculator calculator = new Calculator(display,processor);


  @Test
  public void oneNuberIsPressed() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
    }});
    calculator.numberPressed("5");

  }

  @Test
  public void anotherNuberIsPressed() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("6");
    }});
    calculator.numberPressed("6");

  }

  @Test
  public void twoDigitNumberIsPressed() {

    context.checking(new Expectations() {{

      oneOf(display).displayText("5");
      oneOf(display).displayText("56");

    }});

    calculator.numberPressed("5");
    calculator.numberPressed("6");


  }

  @Test
  public void operatorIsPressed() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5+");
    }});

    calculator.numberPressed("5");
    calculator.operatorPressed("+");

  }

  @Test
  public void operatorIsPressedTwice() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5+");

      never(display).displayText("5++");
    }});

    calculator.numberPressed("5");
    calculator.operatorPressed("+");
    calculator.operatorPressed("+");

  }

  @Test
  public void cleanIsPressed() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("");
    }});

    calculator.cleanPressed();

  }

  @Test
  public void operatorIsPressedAfterClean() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("");
    }});

    calculator.cleanPressed();
    calculator.operatorPressed("+");

  }

  @Test
  public void decimalPointIsPressed() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
    }});

    calculator.numberPressed("5");
    calculator.decimalPressed();

  }

  @Test
  public void decimalPointIsPressedAfterClean() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("");
      oneOf(display).displayText("");

      never(display).displayText(".");

    }});
    calculator.cleanPressed();
    calculator.decimalPressed();

  }

  @Test
  public void decimalPointIsPressedTwiceInARow() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.");

      never(display).displayText("5..");

    }});

    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.decimalPressed();

  }

  @Test
  public void decimalPointIsPressedTwiceInOneDigit() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.5");
      oneOf(display).displayText("5.5");

      never(display).displayText("5.5.");

    }});

    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.numberPressed("5");
    calculator.decimalPressed();

  }

  @Test
  public void twoDigitsEntered() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5+");
      oneOf(display).displayText("5+8");

    }});

    calculator.numberPressed("5");
    calculator.operatorPressed("+");
    calculator.numberPressed("8");

  }

  @Test
  public void twoDigitsEnteredWithDecimalsInThem() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.5");
      oneOf(display).displayText("5.5+");
      oneOf(display).displayText("5.5+8");
      oneOf(display).displayText("5.5+8.");
      oneOf(display).displayText("5.5+8.8");

    }});

    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.numberPressed("5");
    calculator.operatorPressed("+");
    calculator.numberPressed("8");
    calculator.decimalPressed();
    calculator.numberPressed("8");

  }

  @Test
  public void twoDigitsEnteredAfterCleanWithDecimalsInThem() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.5");

      oneOf(display).displayText("");
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.5");
      oneOf(display).displayText("5.5+");
      oneOf(display).displayText("5.5+8");
      oneOf(display).displayText("5.5+8.");
      oneOf(display).displayText("5.5+8.8");

    }});

    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.numberPressed("5");


    calculator.cleanPressed();
    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.numberPressed("5");
    calculator.operatorPressed("+");
    calculator.numberPressed("8");
    calculator.decimalPressed();
    calculator.numberPressed("8");

  }

  @Test
  public void eaqualsIsPressed() {

    context.checking(new Expectations(){{

      oneOf(display).displayText("");
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.5");
      oneOf(display).displayText("5.5+");
      oneOf(display).displayText("5.5+8");
      oneOf(display).displayText("5.5+8.");
      oneOf(display).displayText("5.5+8.8");

      oneOf(processor).calculate("5.5+8.8");
      will(returnValue("14.3"));

      oneOf(display).displayText("14.3");


    }});

    calculator.cleanPressed();
    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.numberPressed("5");
    calculator.operatorPressed("+");
    calculator.numberPressed("8");
    calculator.decimalPressed();
    calculator.numberPressed("8");
    calculator.eaqualsPressed();

  }

  @Test
  public void eaqualsIsPressedAfterOperator() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5+");
      oneOf(display).displayText("5+");

    }});

    calculator.numberPressed("5");
    calculator.operatorPressed("+");
    calculator.eaqualsPressed();

  }

  @Test
  public void eaqualsIsPressedAfterDecimal() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5.");
      oneOf(display).displayText("5.");

    }});

    calculator.numberPressed("5");
    calculator.decimalPressed();
    calculator.eaqualsPressed();

  }

  @Test
  public void eaqualsIsPressedAfterClean() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("");
      oneOf(display).displayText("");

    }});

    calculator.numberPressed("5");
    calculator.cleanPressed();
    calculator.eaqualsPressed();

  }

  @Test
  public void eaqualsIsAfterEaquals() {

    context.checking(new Expectations(){{
      oneOf(display).displayText("5");
      oneOf(display).displayText("5");
      oneOf(display).displayText("5");

    }});

    calculator.numberPressed("5");
    //calculator.cleanPressed();
    calculator.eaqualsPressed();
    calculator.eaqualsPressed();

  }


}