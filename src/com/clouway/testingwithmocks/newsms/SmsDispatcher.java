package com.clouway.testingwithmocks.newsms;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:30 Oct 14-10-22
 */
public class SmsDispatcher {

  public void dispatch(Validator smsValidator, SmsGateway gateway, Sms sms) {
    List<String> validationList=smsValidator.validate(sms);
    if (validationList.size() == 0) {
      gateway.send(sms);
    }
    else{
      //System.out.println(validationList.toString());
    }
  }
}
