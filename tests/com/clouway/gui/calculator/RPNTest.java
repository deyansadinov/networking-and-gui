package com.clouway.gui.calculator;

import com.clouway.gui.calculator.calculation.RPN;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RPNTest {

//  @Test
//  public void createRPNNotation() throws Exception {
//    RPN rpn = new RPN();
//    String equation = "1*2+4/2+37";
//    String rpnEquation = rpn.toPostfix(equation);
//    assertThat(rpnEquation.split(","), arrayWithSize(9));
//    assertThat(rpnEquation, is(equalTo("1,2,*,4,2,/,+,37,+,")));
//  }

  @Test
  public void calculatePostfix() throws Exception {
    RPN rpn = new RPN();
    String postfixNotation = "1,2,*,4,2,/,+,";
    double result = rpn.evaluate(postfixNotation);
    assertThat(result, is(4.0));
  }

  @Test
  public void calculateSecondPostfix() throws Exception {
    RPN rpn = new RPN();
    String postfixNotation = "30,10,5,*,2,/,+,";
    double result = rpn.evaluate(postfixNotation);
    assertThat(result, is(55.0));
  }

//  @Test
//  public void calculateEquationWithSumMultiplyAndPower() throws Exception {
//    RPN rpn = new RPN();
//    String equation = "2+3*2^2*3";
//    String postfixNotation = rpn.toPostfix(equation);
//    assertThat(postfixNotation, is(equalTo("2,3,2,2,^,*,3,*,+,")));
//    double result = rpn.evaluate(postfixNotation);
//    assertThat(result, is(equalTo(38.0)));
//  }

//  @Test
//  public void calculateEquationWithSumMultiplyAndPowerToThePower() throws Exception {
//    RPN rpn = new RPN();
//    String equation = "2+3*2^2^2*3";
//    String postfixNotation = rpn.toPostfix(equation);
//    assertThat(postfixNotation, is(equalTo("2,3,2,2,^,2,^,*,3,*,+,")));
//    double result = rpn.evaluate(postfixNotation);
//    assertThat(result, is(equalTo(146.0)));
//  }

}
