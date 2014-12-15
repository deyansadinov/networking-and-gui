package com.clouway.networkingandgui.calculator;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:36 Nov 14-11-27
 */
public class SampleTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();


  public interface SomeInteface {
    void doSomething();
  }


  @Test
  public void testtestt() throws Exception {
    final SomeInteface someInteface = context.mock(SomeInteface.class);

    context.checking(new Expectations() {{
      oneOf(someInteface).doSomething();
    }});


    someInteface.doSomething();
    someInteface.doSomething();


  }
}
