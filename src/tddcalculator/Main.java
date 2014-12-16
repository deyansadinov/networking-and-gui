package tddcalculator;

import java.util.HashMap;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Main {
  public static void main(String[] args) {
    CalculatorUI calculatorUI = new CalculatorUI();
    Calculator calculator = new Calculator(calculatorUI,new HashMap<String, String>(){{
      put("+","+");
      put("-","-");
      put("*","*");
      put("/","/");
    }});


    calculatorUI.createFrame(calculator);
  }
}
