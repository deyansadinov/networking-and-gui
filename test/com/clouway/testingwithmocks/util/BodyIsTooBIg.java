package com.clouway.testingwithmocks.util;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.awt.Window.Type;
import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:45 Nov 14-11-19
 */
public class BodyIsTooBIg extends TypeSafeMatcher<List<String>> {

  private String message="Body is too big!";
  @Override
  protected boolean matchesSafely(List<String> strings) {
    return strings.contains(message);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Should match : ").appendText(message);

  }
}
