package tddcalculator;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Calculator implements CalculatorListener {
  private String currentDisplay = "";
  private final Display display;
  private final Map<String, String> operators;


  private boolean lastCharacterOperation = false;
  private boolean pointFlag = false;

  public Calculator(Display display, Map<String, String> operators) {

    this.display = display;
    this.operators = operators;
  }


  @Override
  public void onNumberPressed(Integer symbol) {

    lastCharacterOperation = false;

    currentDisplay += symbol;
    display.displayText(currentDisplay);
  }

  @Override
  public void onOperationPressed(String operation) {

    if (!operators.containsKey(operation)) {
      return;
    }
    if (!isOperation(currentDisplay)) {
      lastCharacterOperation = false;
    }

    if (currentDisplay.endsWith(".")) {
      return;
    }

    for (Map.Entry<String, String> entry : operators.entrySet()) {
      if (currentDisplay.endsWith(entry.getValue())) {
        return;
      }
    }


//    if (!lastCharacterOperation) {
//      lastCharacterOperation = true;
//      pointFlag = false;

    currentDisplay += operation;
    display.displayText(currentDisplay);
//    }


  }

  @Override
  public void onEvaluate() {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine eng = mgr.getEngineByName("JavaScript");
    Double result;
    try {
      result = (Double) eng.eval(currentDisplay);
      currentDisplay = String.valueOf(result);
      display.displayText(currentDisplay);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
//    if (result != null) {
//      display.displayText(result.toString());
//    }
////    display.getText();
//    getCurrentDisplay();
  }

  @Override
  public void onDeletePressed() {
    lastCharacterOperation = false;
    pointFlag = false;
    currentDisplay = "";
    display.displayText(currentDisplay);
  }

  @Override
  public void onBackspacePressed() {

    if (currentDisplay.equals("")) {
      return;
    }
//    String lastSymbol = expression.substring(expression.length() - 1);
//    if (isOperation(lastSymbol)) {
//      pointFlag = true;
//      lastCharacterOperation = false;
//    }
//    display.displayText(expression.substring(0, expression.length() - 1));
    String[] exp = currentDisplay.split("");
    exp[exp.length - 1] = "";
    currentDisplay = "";
    for (String x : exp) {
      currentDisplay += x;
      if (!isOperation(currentDisplay)) {
        pointFlag = true;
        lastCharacterOperation = false;
      }
    }
    display.displayText(currentDisplay);
  }

  private boolean isOperation(String operator) {
    String operations = "+-/*";
    return operations.contains(operator);
  }


  @Override
  public void onPointPressed() {

    if (currentDisplay.length() == 0) {
      return;
    }
    String point = ".";
    if (!pointFlag) {
      pointFlag = true;
    }
    for (Map.Entry<String, String> entry : operators.entrySet()) {
      if (currentDisplay.endsWith(entry.getValue())) {
        return;
      }
    }
    int count = 0;
    String[] exp = currentDisplay.split("");
    for (String x : exp) {
      if (x.equals(point)) {
        count++;
      }
      for (Map.Entry<String, String> entry : operators.entrySet()) {
        if (x.equals(entry.getValue())) {
          count--;
        }
      }
    }
    if (count >= 1) return;
    currentDisplay += point;

    display.displayText(currentDisplay);


  }


}
