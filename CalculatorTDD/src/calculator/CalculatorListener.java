package calculator;

/**
 * Created on 14-11-28.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public interface CalculatorListener {

  void numberPressed(String text);

  void operatorPressed(String text);

  void cleanPressed();

  void decimalPressed();

  void eaqualsPressed();
}
