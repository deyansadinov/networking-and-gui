package com.clouway.testingwithmocks.smstoday;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:02 Oct 14-10-30
 */
public class Dispatcher {

  private Validator validator;

  public Dispatcher(Validator validator) {

    this.validator = validator;
  }

  public void dispatch(Sms sms, Gateway gateway) {
    List<String> outOut=validator.validate(sms);
    if(outOut.size()==0){
      gateway.send(sms);
    }else{
      System.out.println(outOut.toString());
    }
  }
}
