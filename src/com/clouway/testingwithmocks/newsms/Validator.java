package com.clouway.testingwithmocks.newsms;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:40 Oct 14-10-22
 */
public interface Validator {
  List<String> validate(Sms sms);
}
