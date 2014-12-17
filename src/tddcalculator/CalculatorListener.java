package tddcalculator;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public interface CalculatorListener {

  void onNumberPressed(Integer number);

  void onOperationPressed(String text);

  void onEvaluate();

  void onClearPressed();

  void onBackspacePressed();

  void onPointPressed();
}
