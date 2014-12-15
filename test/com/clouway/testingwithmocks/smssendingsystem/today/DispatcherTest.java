//package com.clouway.testingwithmocks.smssendingsystem.today;
//
//
//import com.google.common.collect.Lists;
//import org.jmock.Expectations;
//import org.jmock.integration.junit4.JUnitRuleMockery;
//import org.junit.Rule;
//import org.junit.Test;
//
//public class DispatcherTest {
//  @Rule
//  public JUnitRuleMockery context = new JUnitRuleMockery();
//
//  @Test
//  public void sendGoogSMS() throws Exception {
//    final SMS sms = new SMS("0987656789", "title", "sample.txt text");
//    final Gateway gateway = context.mock(Gateway.class);
//    final Validator validator=context.mock(Validator.class);
//    Dispatcher dispatcher = new Dispatcher(gateway,validator);
//
//    context.checking(new Expectations() {{
//      oneOf(validator).validate(sms);will(returnValue(Lists.newArrayList()));
//      oneOf(gateway).send(sms);
//    }});
//
//    dispatcher.dispatch(sms);
//
//  }
//  @Test
//  public void sendBadSMS() throws Exception {
//    final SMS sms = new SMS("0987656789", "title", "sample.txt text");
//    final Gateway gateway = context.mock(Gateway.class);
//    final Validator validator=context.mock(Validator.class);
//    Dispatcher dispatcher = new Dispatcher(gateway,validator);
//
//    context.checking(new Expectations() {{
//      oneOf(validator).validate(sms);will(returnValue(Lists.newArrayList(1)));
//      never(gateway).send(sms);
//    }});
//
//    dispatcher.dispatch(sms);
//
//  }
//}