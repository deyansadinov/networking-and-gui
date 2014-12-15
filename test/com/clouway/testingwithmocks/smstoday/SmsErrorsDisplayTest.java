package com.clouway.testingwithmocks.smstoday;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:00 Oct 14-10-30
 */
public class SmsErrorsDisplayTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void displaysAllErrors() throws Exception {

    final Sms sms = new Sms("", "", "");
    final Gateway gateway = context.mock(Gateway.class);
    final Validator validator = new SmsValidator(10,1,5);
    Dispatcher dispatcher = new Dispatcher(validator);

    context.checking(new Expectations() {{

      never(gateway).send(sms);
    }});

    dispatcher.dispatch(sms, gateway);

  }

  @Test
  public void displayOnlyOneError() throws Exception {

    final Sms sms = new Sms("1234567895", "", "sdasdsad");
    final Gateway gateway = context.mock(Gateway.class);
    final Validator validator = new SmsValidator(10,1,5);
    Dispatcher dispatcher = new Dispatcher(validator);

    context.checking(new Expectations() {{

      never(gateway).send(sms);
    }});

    dispatcher.dispatch(sms, gateway);

  }

  @Test
  public void displayTwoErrors() throws Exception {
    final Sms sms = new Sms("123456785", "", "sdasdsad");
    final Gateway gateway = context.mock(Gateway.class);
    final Validator validator = new SmsValidator(10,1,5);
    Dispatcher dispatcher = new Dispatcher(validator);

    context.checking(new Expectations() {{

      never(gateway).send(sms);
    }});

    dispatcher.dispatch(sms, gateway);

  }
}
