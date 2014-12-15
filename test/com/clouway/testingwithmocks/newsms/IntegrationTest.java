package com.clouway.testingwithmocks.newsms;

import com.google.common.collect.Lists;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:25 Oct 14-10-24
 */
public class IntegrationTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();


  @Test
  public void sendValidSMS() throws Exception {
    final SmsGateway gateway = context.mock(SmsGateway.class);
    final Validator validator = new SmsValidator();
    SmsReceiver smsReceiver = new SmsReceiver("0987898767");
    SmsTitle smsTitle=new SmsTitle("Some Title");
    SmsBody smsBody=new SmsBody("This is asimple text to chech my sms system.");
    final Sms sms = new Sms(smsReceiver, smsTitle, smsBody);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    context.checking(new Expectations() {{

      oneOf(gateway).send(sms);
    }});

    smsDispatcher.dispatch(validator, gateway, sms);
  }

  @Test
  public void sendInvalidSMS() throws Exception {

    final SmsGateway gateway = context.mock(SmsGateway.class);
    final Validator validator = new SmsValidator();
    SmsReceiver smsReceiver = new SmsReceiver("123567890");
    SmsTitle smsTitle=new SmsTitle("Some Title");
    SmsBody smsBody=new SmsBody("This is asimple text to chech my sms system.");
    final Sms sms = new Sms(smsReceiver, smsTitle, smsBody);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    context.checking(new Expectations() {{
      never(gateway).send(sms);
    }});

    smsDispatcher.dispatch(validator, gateway, sms);
  }

  @Test
  public void chechForTheReceiverNumber() throws Exception {
    final SmsGateway gateway = context.mock(SmsGateway.class);
    final SmsValidator smsValidator = new SmsValidator();
    SmsReceiver smsReceiver = new SmsReceiver("0988918767");
    SmsTitle smsTitle=new SmsTitle("Some Title");
    SmsBody smsBody=new SmsBody("This is asimple text to chech my sms system.");
    final Sms sms = new Sms(smsReceiver, smsTitle, smsBody);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    context.checking(new Expectations() {{
      oneOf(gateway).send(sms);
    }});

    smsDispatcher.dispatch(smsValidator, gateway, sms);
  }

  @Test
  public void checkForTitleValidation() throws Exception {
    final SmsGateway gateway = context.mock(SmsGateway.class);
    final SmsValidator smsValidator = new SmsValidator();
    SmsReceiver smsReceiver = new SmsReceiver("0988918767");
    SmsTitle smsTitle=new SmsTitle("a");
    SmsBody smsBody=new SmsBody("This is asimple text to chech my sms system.");
    final Sms sms = new Sms(smsReceiver,smsTitle, smsBody);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    context.checking(new Expectations() {{
      oneOf(gateway).send(sms);
    }});

    smsDispatcher.dispatch(smsValidator, gateway, sms);

  }

  @Test
  public void checkForSmsBodyValidation() throws Exception {

    final SmsGateway gateway = context.mock(SmsGateway.class);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    final SmsValidator smsValidator = new SmsValidator();
    final Sms sms = new Sms(new SmsReceiver("8098789097"),new SmsTitle("The Title"),new SmsBody("Simple sample.txt text."));

    context.checking(new Expectations() {{
      oneOf(gateway).send(sms);
    }});

    smsDispatcher.dispatch(smsValidator, gateway, sms);
  }

  @Test
  public void clientFeedBack() throws Exception {

    final SmsGateway gateway = context.mock(SmsGateway.class);
    SmsDispatcher smsDispatcher = new SmsDispatcher();

    final SmsValidator smsValidator = new SmsValidator();
    final Sms sms = new Sms(new SmsReceiver("0987897"),new SmsTitle("The Title"),new SmsBody("Simple sample.txt text."));

    smsDispatcher.dispatch(smsValidator, gateway, sms);

  }


}
