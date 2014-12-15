package com.clouway.testingwithmocks.newsms;

import com.google.common.collect.Lists;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SmsDispatcherTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void sendValidSMS() throws Exception {
    final SmsGateway gateway = context.mock(SmsGateway.class);
    final Validator validator = context.mock(Validator.class);
    SmsReceiver smsReceiver = new SmsReceiver("0987898767");
    SmsTitle smsTitle=new SmsTitle("Some Title");
    SmsBody smsBody=new SmsBody("This is asimple text to chech my sms system.");
    final Sms sms = new Sms(smsReceiver, smsTitle, smsBody);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    context.checking(new Expectations() {{
      oneOf(validator).validate(sms);
      will(returnValue(Lists.newArrayList()));
      oneOf(gateway).send(sms);
    }});

    smsDispatcher.dispatch(validator, gateway, sms);
  }

  @Test
  public void sendInvalidSMS() throws Exception {

    final SmsGateway gateway = context.mock(SmsGateway.class);
    final Validator validator = context.mock(Validator.class);
    SmsReceiver smsReceiver = new SmsReceiver("123567890");
    SmsTitle smsTitle=new SmsTitle("Some Title");
    SmsBody smsBody=new SmsBody("This is asimple text to chech my sms system.");
    final Sms sms = new Sms(smsReceiver, smsTitle, smsBody);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    context.checking(new Expectations() {{
      oneOf(validator).validate(sms);
     will(returnValue(Lists.newArrayList(1)));
      never(gateway).send(sms);
    }});

    smsDispatcher.dispatch(validator, gateway, sms);
  }
}