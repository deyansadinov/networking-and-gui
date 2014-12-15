package com.clouway.testingwithmocks.util;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 12:04 Nov 14-11-15
 */
public class TitleIsEmpty extends TypeSafeMatcher<List<String>> {
  private final String message = "Cant have an epmty title!";

  @Override
  protected boolean matchesSafely(List<String> strings) {
    return strings.contains(message);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText(" to be ").appendText(message);
  }
}
