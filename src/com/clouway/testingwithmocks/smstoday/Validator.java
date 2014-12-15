package com.clouway.testingwithmocks.smstoday;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:10 Oct 14-10-30
 */
public interface Validator {
  List<String> validate(Sms sms);
}
