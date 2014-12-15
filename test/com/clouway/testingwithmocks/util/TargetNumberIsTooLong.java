package com.clouway.testingwithmocks.util;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:45 Nov 14-11-15
 */
public class TargetNumberIsTooLong extends TypeSafeMatcher<List<String>> {

  private final String message = "Target number is too long.";

  @Override
  protected boolean matchesSafely(List<String> strings) {
    return strings.contains(message);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Should match : ").appendText(message);
  }
}
