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
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
//public class ReceiverRuleTenNumbersTest {
//
//  @Rule
// public JUnitRuleMockery context=new JUnitRuleMockery();
//
//  @Test
//  public void receiverRuleTenNumbers() throws Exception {
//    final SMS sms=new SMS("1234567890","Tiytl","SSSSS");
//    final ValidatorRules validatorRules=new ReceiverRuleTenNumbers();
//    List<ValidatorRules> oneRuleList= Lists.newArrayList();
//    oneRuleList.put(validatorRules);
//    SMSValidator smsValidator=new SMSValidator(oneRuleList);
//
//    List list=smsValidator.validate(sms);
//    assertThat(list.size(),is(0));
//  }


//}