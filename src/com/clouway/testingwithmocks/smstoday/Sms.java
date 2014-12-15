package com.clouway.testingwithmocks.smstoday;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:05 Oct 14-10-30
 */
public class Sms {
  private String receiver;
  private String title;
  private String body;

  public Sms(String receiver, String title, String body) {

    this.receiver = receiver;
    this.title = title;
    this.body = body;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getBody() {
    return body;
  }

  public String getTitle() {
    return title;
  }
}
