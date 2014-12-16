package test2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Calculator {


  private CalculatorView view;
  private boolean lastCharacterOperation = false;
  private boolean pointFlag = false;

  public Calculator(CalculatorView calculatorView) {

    this.view = calculatorView;
  }

  /**
   * Adding number on calculator display and change lastCharacterOperation
   *
   * @param symbol that added
   */
  public String numberPressed(char symbol) {
    lastCharacterOperation = false;
    return view.setExpression(view.getExpression() + symbol);
  }

  /**
   * Reset display on calculator
   */
  public String deletePressed() {
    lastCharacterOperation = false;
    pointFlag = false;
    return view.setExpression("");

  }

  /**
   * Remove last character on display
   */
  public String backspacePressed() {
    String expression = view.getExpression();
    if (expression.equals("")) {
      return "";
    }
    String lastSymbol = expression.substring(expression.length() - 1);
    if (isOperation(lastSymbol)) {
      pointFlag = true;
      lastCharacterOperation = false;
    }

    return view.setExpression(expression.substring(0, (expression.length() - 1)));
  }

  /**
   * Check whether last symbol is operation
   *
   * @param lastSymbol
   * @return if last symbol is operation return true or false if is not
   */
  private boolean isOperation(String lastSymbol) {
    String operations = "+-/*";
    return operations.contains(lastSymbol);
  }

  /**
   * Calculate expression on display
   *
   * @return
   */
  public String calculateExpression() {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine eng = mgr.getEngineByName("JavaScript");
    Double result = null;
    try {
      result = (Double) eng.eval(view.getExpression());
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    if (result != null) {
      view.setExpression(result.toString());
    }
    return view.getExpression();

  }

  /**
   * Adding operation on calculator display and change lastCharacterOperation and pointFlag
   *
   * @param symbol that added
   */
  public String operationPressed(char symbol) {
    if (!lastCharacterOperation) {
      lastCharacterOperation = true;
      pointFlag = false;
      return view.setExpression(view.getExpression() + symbol);
    }
    return view.getExpression();
  }

  /**
   * Adding point on calculator display and change pointFlag
   */
  public String pointPressed() {
    if (!pointFlag) {
      pointFlag = true;

    return   view.setExpression(view.getExpression() + ".");
    }
   return view.getExpression();
  }
}
