//package com.clouway.testingwithmocks.sms.rules;
//
//import com.clouway.testingwithmocks.sms.SMS;
//import com.clouway.testingwithmocks.sms.SMSValidator;
//import com.clouway.testingwithmocks.sms.Validator;
//import com.clouway.testingwithmocks.sms.ValidatorRules;
//import com.google.common.collect.Lists;
//import org.jmock.Expectations;
//import org.jmock.integration.junit4.JUnitRuleMockery;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.Is.is;
//
///**
// * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:04 Oct 14-10-16
// */
//public class EmptyTitleRuleTest {
//
//  ValidatorRules numberLengthRule, onlyNumbersRule;
//  @Rule
//  public JUnitRuleMockery context = new JUnitRuleMockery();
//
//  @Test
//  public void emptyTitle() throws Exception {
//
////    EmptyTitleRule emptyTitle=new EmptyTitleRule();
////    SMS sms=new SMS("1234567802","a","text");
////    SMSValidator smsValidator=new SMSValidator(emptyTitle);
////
////    List list=smsValidator.validate(sms);
////    assertThat(list.size(),is(0));
//  }
//
//  @Test
//  public void multipleRuleTest() throws Exception {
//    numberLengthRule = context.mock(ValidatorRules.class,"numberLength");
//    onlyNumbersRule = context.mock(ValidatorRules.class,"onlyNumbers");
//    final SMS sms=new SMS("1234567802","a","text");
//
//    context.checking(new Expectations(){{
//      oneOf(numberLengthRule).theRule(sms);
//      oneOf(onlyNumbersRule).theRule(sms);
//    }});
//
//    List<ValidatorRules> ruleList = Lists.newArrayList(numberLengthRule,onlyNumbersRule);
//    SMSValidator validator = new SMSValidator(ruleList);
//    validator.validate(sms);
//  }
//
//  @Test
//  public void multipleRuleTestWithError() throws Exception {
//    numberLengthRule = context.mock(ValidatorRules.class,"numberLength");
//    onlyNumbersRule = context.mock(ValidatorRules.class,"onlyNumbers");
//    final SMS sms=new SMS("1234567802","aaaa","text");
//
//    context.checking(new Expectations(){{
//      oneOf(numberLengthRule).theRule(sms); will(returnValue("some error"));
//      oneOf(onlyNumbersRule).theRule(sms);
//    }});
//
//    List<ValidatorRules> ruleList = Lists.newArrayList(numberLengthRule,onlyNumbersRule);
//    SMSValidator validator = new SMSValidator(ruleList);
//    List s=validator.validate(sms);
//    assertThat(s.size(),is(1));
//  }
//}
