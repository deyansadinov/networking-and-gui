package com.clouway.testingwithmocks.newsms;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:32 Oct 14-10-22
 */
public class SmsReceiver {
  private String number;

  public SmsReceiver(String number) {
    this.number = number;
  }

  public String getNumber() {
    return number;
  }
}
