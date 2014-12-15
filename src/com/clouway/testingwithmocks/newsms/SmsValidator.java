package com.clouway.testingwithmocks.newsms;

import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:16 Oct 14-10-22
 */
public class SmsValidator implements Validator {

  @Override
  public List<String> validate(Sms sms) {
    List<String> newList = new ArrayList<>();
    if (sms.getNumberFromReceiver().length() != 10) {
      newList.add("Incorect receiver  number !");
    }
    if (sms.getTheTitle().length() == 0) {
      newList.add("Title must contain atleast one char !");
    }
    if (sms.getTheBody().length() < 1 || sms.getTheBody().length() > 120) {
      newList.add("The body text must be from 1 - 120 chars !");
    }
    return newList;
  }

}
