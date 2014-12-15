package com.clouway.testingwithmocks.newsms;

import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:14 Oct 14-10-22
 */
public class Sms {
  private SmsReceiver smsReceiver;
  private SmsTitle smsTitle;
  private SmsBody smsBody;

  public Sms(SmsReceiver smsReceiver, SmsTitle smsTitle, SmsBody smsBody) {
    this.smsReceiver = smsReceiver;
    this.smsTitle = smsTitle;
    this.smsBody = smsBody;
  }

  public SmsReceiver getSmsReceiver() {
    return smsReceiver;
  }

  public String getNumberFromReceiver() {
    return smsReceiver.getNumber();
  }

  public SmsTitle getSmsTitle() {
    return smsTitle;
  }
  public String getTheTitle(){
    return smsTitle.getTitle();
  }

  public SmsBody getSmsBody() {
    return smsBody;
  }
  public  String getTheBody(){
    return smsBody.getBody();
  }
}
