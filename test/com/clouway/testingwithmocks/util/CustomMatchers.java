package com.clouway.testingwithmocks.util;

import com.clouway.testingwithmocks.today.Errors;
import org.hamcrest.Matcher;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:51 Nov 14-11-19
 */
public class CustomMatchers {

  public static Matcher<List<String>> bodyIsTooBig() {
    return new BodyIsTooBIg();
  }

  public static Matcher<Errors> hasError(String message) {
    return new ErrorMatcher(message);
  }
}
