package calculator;

/**
 * Created on 14-11-28.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class Calculator implements CalculatorListener {

  private final Display display;
  private final MathProcessor processor;

  private String expression = "";

  private boolean operatorPressed = false;
  private boolean decimalPressed = false;

  public Calculator(Display display, MathProcessor processor) {

    this.display = display;
    this.processor = processor;
  }

  @Override
  public void numberPressed(String text) {

    expression = expression + text;
    display.displayText(expression);
  }

  @Override
  public void operatorPressed(String text) {

    if (!expression.equals("") && !operatorPressed) {
      expression = expression + text;
      display.displayText(expression);
      operatorPressed = true;
      decimalPressed = false;
    }
  }

  @Override
  public void cleanPressed() {
    expression = "";
    display.displayText(expression);
    operatorPressed = false;
    decimalPressed = false;

  }

  @Override
  public void decimalPressed() {

    if (!expression.equals("") && !hasOperatorOrDecimalAsLastSign() && !decimalPressed) {
      expression += ".";
      display.displayText(expression);
      decimalPressed = true;
    } else display.displayText(expression);

  }

  @Override
  public void eaqualsPressed() {

    if (!hasOperatorOrDecimalAsLastSign() && !expression.equals("")&&operatorPressed) {
      expression = processor.calculate(expression);
      display.displayText(expression);
      operatorPressed=false;
    } else {
      display.displayText(expression);
    }
  }

  private boolean hasOperatorOrDecimalAsLastSign() {
    if (!expression.equals("")) {
      String lastChar = String.valueOf(expression.charAt(expression.length() - 1));

      return lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("*")
              || lastChar.equals("/") || lastChar.equals(".");
    } else {
      return false;
    }
  }
}
