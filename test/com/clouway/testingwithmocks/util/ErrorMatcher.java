package com.clouway.testingwithmocks.util;


import com.clouway.testingwithmocks.today.Errors;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:14 Nov 14-11-19
 */
public class ErrorMatcher extends TypeSafeMatcher<Errors> {


  private String message;

  public ErrorMatcher(String message) {

    this.message = message;
  }

  @Override
  protected boolean matchesSafely(Errors errors) {
    return errors.getError().equals(message);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Should match : ").appendText(message);
  }
}
