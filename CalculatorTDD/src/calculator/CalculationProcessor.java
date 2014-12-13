package calculator;

import java.util.Arrays;

/**
 * Created on 14-12-2.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class CalculationProcessor implements MathProcessor {

  @Override
  public String calculate(String expression) {
    Double result = 0.0;
    String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

    char operator = tokens[1].charAt(0);

    switch (operator) {

      case '+':
        result = (Double.valueOf(tokens[0]) + Double.valueOf(tokens[2]));
        break;
      case '-':
        result = (Double.valueOf(tokens[0]) - Double.valueOf(tokens[2]));
        break;
      case '*':
        result = (Double.valueOf(tokens[0]) * Double.valueOf(tokens[2]));
        break;
      case '/':
        result = (Double.valueOf(tokens[0]) / Double.valueOf(tokens[2]));
        break;

    }

    return result.toString();
  }
}
