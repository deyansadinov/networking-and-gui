//package com.clouway.testingwithmocks.smssendingsystem.today;
//
//import org.jmock.Expectations;
//import org.jmock.integration.junit4.JUnitRuleMockery;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
//public class NullTitleRuleTest {
//
//  @Rule
//  public JUnitRuleMockery context = new JUnitRuleMockery();
//
//  @Test
//  public void nullTitle() throws Exception {
//    final ValidatorRules validatorRules=context.mock(ValidatorRules.class);
//    Validator validator=new NullTitleRule(validatorRules);
//    final SMS sms = new SMS("0987656789", "title", "sample.txt text");
//
//    context.checking(new Expectations(){{
//      oneOf(validatorRules).theRule(sms);
//    }});
//    List list=validator.validate(sms);
//    assertThat(list.size(),is(0));
//  }
//}