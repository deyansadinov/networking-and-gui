package com.clouway.testing.sample.introducingjava;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ArrayTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  public List<Integer> list = new ArrayList<>();

//  @Test
//  public void print() throws Exception {
//    int[] arr = new int[]{1, 2, 3, 4, 5};
//    Array array = new Array(arr);
//    final StringBuilder builder = new StringBuilder();
//    array.print(new Display() {
//      @Override
//      public void printInterface(int x) {
//        builder.append(x);
//      }
//    });
//
//    assertThat(builder.toString(), is("12345"));
//  }

  @Test
  public void testPrintWithJMock() throws Exception {
    final Display display = context.mock(Display.class);

    final int[] arr = new int[]{1, 2, 3};
    final Array array = new Array(arr);

    list.add(1);
    list.add(2);
    list.add(3);


    context.checking(new Expectations() {{
      allowing(display).printInterface(list);
      will(returnValue(list));

    }});

    array.print(display);


  }

  @Test
  public void sum() throws Exception {
    int[] arr = new int[]{1, 2, 3, 4};
    Array array = new Array(arr);
    int sum = array.getSum();
    assertThat(sum, is(10));
  }

  @Test
  public void minElement() throws Exception {
    int[] arr = new int[]{1, 2, 3, 4};
    Array array = new Array(arr);
    int min = array.getMinElement();
    assertEquals(min, 1);
  }

}