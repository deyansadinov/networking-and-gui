package com.clouway.networkingandgui.tddcalculator;

import java.util.HashMap;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:33 Nov 14-11-26
 */
public class Main {
  public static void main(String[] args) {
    TDDCalculatorUI tddCalculator = new TDDCalculatorUI();

    Calculator calculator=new Calculator(tddCalculator,new HashMap<String,String>(){{
      put("+","+");
      put("-","-");
      put("/","/");
      put("*","*");

    }});

    tddCalculator.init(calculator);

  }
}
