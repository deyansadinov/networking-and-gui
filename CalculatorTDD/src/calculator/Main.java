package calculator;

/**
 * Created on 14-11-28.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class Main {
  public static void main(String[] args) {

    CalculatorUI calculatorUI = new CalculatorUI();


    MathProcessor processor = new CalculationProcessor();


    Calculator calculator = new Calculator(calculatorUI,processor);


    calculatorUI.setVisibility(calculator);



  }
}
