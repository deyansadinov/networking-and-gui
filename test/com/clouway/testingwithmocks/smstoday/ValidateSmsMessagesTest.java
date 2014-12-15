package com.clouway.testingwithmocks.smstoday;

import com.clouway.testingwithmocks.util.BodyIsTooSmall;
import com.clouway.testingwithmocks.util.EmptyTargetNumber;
import com.clouway.testingwithmocks.util.TargetNumberIsTooLShort;
import com.clouway.testingwithmocks.util.TargetNumberIsTooLong;
import com.clouway.testingwithmocks.util.TitleIsEmpty;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateSmsMessagesTest {

  @Test
  public void emptyTargetNumber() throws Exception {
    Sms sms = new Sms("", "aa", "aaa");
    SmsValidator smsValidator = new SmsValidator(2,1,5);

    assertThat( smsValidator.validate(sms),new EmptyTargetNumber());
  }
  @Test
  public void shortTargetNumber() throws Exception {
    Sms sms = new Sms("1234", "aa", "aaa");
    SmsValidator smsValidator = new SmsValidator(6,1,5);

    assertThat(smsValidator.validate(sms),new TargetNumberIsTooLShort());
  }


  @Test
  public void longTargetNumber() throws Exception {
    Sms sms = new Sms("123456789", "a", "assdfsfa");
    SmsValidator smsValidator = new SmsValidator(5,1,51);

    assertThat(smsValidator.validate(sms),new TargetNumberIsTooLong());
  }

  @Test
  public void emptySmsTitle() throws Exception {
    Sms sms = new Sms("1234567898", "", "sssa");
    SmsValidator smsValidator = new SmsValidator(10,1,5);

    assertThat(smsValidator.validate(sms), new TitleIsEmpty());

  }

//  @Test
//  public void bodyTooBig() throws Exception {
//
//    Sms sms = new Sms("1234567898", "a","ghjjdsajsksajl");
//    SmsValidator smsValidator = new SmsValidator(10,1,5);
//
//    Errors errors = smsValidator.validate(sms);
//    assertThat(errors,hasError("body is too big"));
//
//
//    assertThat("test",is(equalTo("test")));
//
//  }

  @Test
  public void smsBodyTooShort() throws Exception {
    Sms sms = new Sms("1234567898", "a","a");
    SmsValidator smsValidator = new SmsValidator(10,3,5);

    assertThat(smsValidator.validate(sms),new BodyIsTooSmall());

  }

  @Test
  public void everyThingIsValidated() throws Exception {

    Sms sms = new Sms("1234567898", "a","aaa");
    SmsValidator smsValidator = new SmsValidator(10,2,5);

    assertThat(smsValidator.validate(sms).size(),is(0));
  }
}