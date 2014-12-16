package test;

  import javax.script.ScriptEngine;
  import javax.script.ScriptEngineManager;
  import javax.script.ScriptException;
/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */


  public class Calculator {

    private boolean isLastCharacterOperation = false;
    private boolean pointFlag = false;
    private  CalculatorView view;

    public Calculator(CalculatorView calculatorView) {
      this.view = calculatorView;
    }

    /**
     * Adding number on calculator display and change "isLastCharacterOperation".
     * @param symbol that added.
     */
    public String onNumberPressed(char symbol) {
      isLastCharacterOperation = false;
      return view.setExpression(view.getExpression() + symbol);
    }

    /**
     * Adding operation on calculator display and change "isLastCharacterOperation" and "pointFlag".
     * @param symbol that added.
     */
    public String onOperationPressed(char symbol) {
      if (!isLastCharacterOperation) {
        isLastCharacterOperation = true;
        pointFlag = false;
        return view.setExpression(view.getExpression() + symbol);
      }
      return view.getExpression();
    }

    /**
     * Calculate expression over display.
     */
    public String onCalculateExpression() {
      ScriptEngineManager mgr = new ScriptEngineManager();
      ScriptEngine engine = mgr.getEngineByName("JavaScript");
      Double result = null;
      try {
        result = (Double) engine.eval(view.getExpression());
      } catch (ScriptException e) {
        e.printStackTrace();
      }
      if (result != null) {
        view.setExpression(result.toString());
      }
      return view.getExpression();
    }

    /**
     * Remove last character from display on calculator.
     */
    public String onBackspacePressed() {
      String expression = view.getExpression();
      if (expression.equals("")) {
        return "";
      }
      String lastSymbol = expression.substring((expression.length() - 1));
      if (isOperation(lastSymbol)) {
        pointFlag = true;
        isLastCharacterOperation = false;
      }
      return view.setExpression(expression.substring(0, (expression.length() - 1)));
    }
    /**
     * Reset display on calculator.
     */
    public String onDeletedPressed() {
      isLastCharacterOperation = false;
      pointFlag = false;
      return view.setExpression("");
    }

    /**
     * Adding point on calculator display and change "pointFlag".
     */
    public String onPointPressed() {
      if (!pointFlag) {
        pointFlag = true;
        return view.setExpression(view.getExpression() + ".");
      }
      return view.getExpression();
    }

    /**
     * Check whether "lastSymbol" is operation.
     * @param lastSymbol
     * @return If "lastSymbol" is operation return true or false it isn't.
     */
    private boolean isOperation(String lastSymbol) {
      String operations = "+-/*";
      return operations.contains(lastSymbol);
    }
  }

