package com.clouway.gui.calculator;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by clouway on 14-12-1.
 */
public class EquationBuildingTest {

  private EquationBuilder eBuilder;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();


  @Before
  public void setUp() throws Exception {
    eBuilder = new EquationBuilder();
  }

  @Test
  public void appendOneNumber() throws Exception {
    eBuilder.append("5");
    assertThat(eBuilder.build(), is(equalTo("5")));
  }

  @Test
  public void appendNumberAndOperand() throws Exception {
    eBuilder.append("6").append("+").append("6");
    assertThat(eBuilder.build(), is(equalTo("6+6")));
  }

  @Test
  public void appendOnePlusTwo() throws Exception {
    eBuilder.append("1").append("+").append("2");
    assertThat(eBuilder.build(), is(equalTo("1+2")));
  }

  @Test
  public void dontAppendMultiplyAsFirstSymbol() throws Exception {
    eBuilder.append("*");
    assertThat(eBuilder.build(), is(equalTo("")));
  }

  @Test
  public void onlyPlusAsFirstSymbol() throws Exception {
    eBuilder.append("+");
    assertThat(eBuilder.build(), is(equalTo("")));
  }

  @Test
  public void plusAfterPlus() throws Exception {
    eBuilder.append("1").append("+").append("+").append("2");
    assertThat(eBuilder.build(),is(equalTo("1+2")));
  }

  @Test
  public void replaceLastOperatorEntered() throws Exception {
    eBuilder.append("1").append("/").append("-").append("4");
    assertThat(eBuilder.build(),is(equalTo("1-4")));
  }

  @Test
  public void dontAppendDotAsFirstSymbol() throws Exception {
    eBuilder.append(".");
    assertThat(eBuilder.build(),is(equalTo("")));
  }

  @Test
  public void appendDotAfterNumber() throws Exception {
    eBuilder.append("1").append(".").append("7");
    assertThat(eBuilder.build(),is(equalTo("1.7")));
  }

  @Test
  public void dontAppendMultipleDots() throws Exception {
    eBuilder.append("1").append(".").append(".").append("10");
    assertThat(eBuilder.build(),is(equalTo("1.10")));
  }

  @Test
  public void dontAppendDotByCreatingInvalidNumber() throws Exception {
    eBuilder.append("1").append(".").append("2").append(".");
    assertThat(eBuilder.build(),is(equalTo("1.2")));
  }

  @Test
  public void multipleDotsInEquation() throws Exception {
    eBuilder.append("1").append(".").append("2").append("+").append("2").append(".").append("4");
    assertThat(eBuilder.build(),is(equalTo("1.2+2.4")));
  }

  @Test
  public void dontInsertOperatorAfterDot() throws Exception {
    eBuilder.append("12").append(".").append("-").append("32");
    assertThat(eBuilder.build(),is(equalTo("12-32")));
  }


  @Test
  public void correctUnfinishedEquationEndingWithDot() throws Exception {
    eBuilder.append("10").append(".");
    assertThat(eBuilder.build(),is(equalTo("")));
  }


}
