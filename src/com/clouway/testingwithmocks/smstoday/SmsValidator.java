package com.clouway.testingwithmocks.smstoday;

import java.util.ArrayList;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:15 Oct 14-10-30
 */
public class SmsValidator implements Validator {

  private int targetSize;
  private final int bodyMinSize;
  private final int bodyMaxSize;


  public SmsValidator(int targetSize, int bodyMinSize, int bodyMaxSize) {
    this.targetSize = targetSize;

    this.bodyMinSize = bodyMinSize;
    this.bodyMaxSize = bodyMaxSize;
  }

  @Override
  public List<String> validate(Sms sms) {
    List<String> validatorList = new ArrayList<>();

    String receiver = sms.getReceiver();
    String title = sms.getTitle();
    String body = sms.getBody();
    //target receiver ------------------
    if (receiver.equals("")) {
      validatorList.add("Target receiver number is empty!");

    } else {
      if (receiver.length() > targetSize) {
        validatorList.add("Target number is too long.");
      }
      if (receiver.length() < targetSize) {
        validatorList.add("Target number is too short.");
      }
      try {
        Integer.parseInt(receiver);
      } catch (NumberFormatException e) {
        validatorList.add("For receiver number use only numbers.");
      }
    }
    if (title.equals("")) {
      validatorList.add("Cant have an epmty title!");
    }
    if (body.equals("")) {
      validatorList.add("Cant have an epmty Body!");
    }
    if (body.length() <= bodyMinSize && body.length() != 0) {
      validatorList.add("Body is too short !");
    }
    if (body.length() >= bodyMaxSize) {
      validatorList.add("Body is too big!");
    }


    return validatorList;
  }
}
