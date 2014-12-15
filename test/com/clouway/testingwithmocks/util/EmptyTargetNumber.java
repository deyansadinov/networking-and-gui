package com.clouway.testingwithmocks.util;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:53 Nov 14-11-15
 */
public class EmptyTargetNumber extends TypeSafeMatcher<List<String>> {

  private final String actual="Target receiver number is empty!";

  @Override
  protected boolean matchesSafely(List<String> strings) {
    return strings.contains(actual);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("Should match : ").appendText(actual);
  }
}
