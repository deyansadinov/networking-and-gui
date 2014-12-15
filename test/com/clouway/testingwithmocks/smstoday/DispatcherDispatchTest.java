package com.clouway.testingwithmocks.smstoday;


import com.google.common.collect.Lists;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class DispatcherDispatchTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void onlyOnceSendSms() throws Exception {
    final Sms sms = new Sms("", "", "");
    final Gateway gateway = context.mock(Gateway.class);
    final Validator validator = context.mock(Validator.class);
    Dispatcher dispatcher = new Dispatcher(validator);

    context.checking(new Expectations() {{
      oneOf(validator).validate(sms);
      oneOf(gateway).send(sms);
    }});

    dispatcher.dispatch(sms, gateway);
  }

  @Test
  public void neverSendSms() throws Exception {
    final Sms sms = new Sms("", "", "");
    final Gateway gateway = context.mock(Gateway.class);
    final Validator validator = context.mock(Validator.class);
    Dispatcher dispatcher = new Dispatcher(validator);

    context.checking(new Expectations() {{
      oneOf(validator).validate(sms);
      will(returnValue(Lists.newArrayList(1)));
      never(gateway).send(sms);
    }});

    dispatcher.dispatch(sms, gateway);

  }

}