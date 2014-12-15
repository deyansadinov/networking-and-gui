package com.clouway.networkingandgui.tddcalculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:33 Nov 14-11-26
 */
public class Calculator implements CalculatorListener {

  private Display display;
  private Map<String, String> symbolMap;
  private String currentDisplay = "";


  public Calculator(Display display, Map<String, String> symbolMap) {

    this.display = display;
    this.symbolMap = symbolMap;
  }

  @Override
  public void numberIsPressed(String text) {
    currentDisplay += text;
    System.out.println(text);
    display.displayText(currentDisplay);

  }

  @Override
  public void operatorIsPressed(String text) {
    if (currentDisplay.length() == 0) {
      return;
    }
    if (currentDisplay.endsWith(".")) {
      return;
    }
    if (!currentDisplay.endsWith(text)) {
      for (Map.Entry<String, String> entry : symbolMap.entrySet()) {
        if (currentDisplay.endsWith(entry.getValue())) {
          return;
        }
      }
      currentDisplay += text;
      display.displayText(currentDisplay);
    }

  }

  @Override
  public void clearIsPressed(String text) {
    currentDisplay = "";
    display.displayText(currentDisplay);
  }

  @Override
  public String getText() {
    return currentDisplay;
  }

  @Override
  public void removeIsPressed(String text) {
    if (currentDisplay.length() == 0) {
      display.displayText(currentDisplay);
      return;
    }
    String[] removed = currentDisplay.split("");
    currentDisplay = "";
    removed[removed.length - 1] = "";
    for (String x : removed) {
      currentDisplay += x;
    }
    display.displayText(currentDisplay);
  }

  @Override
  public void dotIsPressed(String text) {
    if (currentDisplay.length() == 0) {
      return;
    }
    int count = 0;
    for (Map.Entry<String, String> entry : symbolMap.entrySet()) {
      if (currentDisplay.endsWith(entry.getValue())) {
        display.displayText(currentDisplay);
        return;
      }
    }
    String[] theDisplay = currentDisplay.split("");
    for (String x : theDisplay) {
      if (x.equals(text)) {
        count++;
      }
      for (Map.Entry<String, String> entry : symbolMap.entrySet()) {
        if (x.equals(entry.getValue())) {
          count--;
        }
      }
    }
    if (count >= 1) return;
    currentDisplay += text;
    display.displayText(currentDisplay);
  }

  @Override
  public void eqIsPressed(String text) {
    ScriptEngineManager scm = new ScriptEngineManager();
    ScriptEngine jsEngine = scm.getEngineByName("JavaScript");
    try {
      String result=jsEngine.eval(currentDisplay).toString();
      currentDisplay=result;
      display.displayText(result);
      try {
        Thread.sleep(10);
        currentDisplay="";
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } catch (ScriptException e) {
      e.printStackTrace();
    }
  }
}
