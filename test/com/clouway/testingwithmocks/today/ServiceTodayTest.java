package com.clouway.testingwithmocks.today;

import org.junit.Test;

import static com.clouway.testingwithmocks.util.CustomMatchers.hasError;
import static org.hamcrest.MatcherAssert.assertThat;


public class ServiceTodayTest {

  @Test
  public void notAdult() throws Exception {

    Service service = new Service();

    Person person=new Person("Ivan" ,2);

    Errors errors=service.isAdult(person);

    assertThat(errors, hasError("Is not adult."));

  }

  @Test
  public void isAdult() throws Exception {
    Service service = new Service();

    Person person=new Person("Ivan" ,22);

    Errors errors=service.isAdult(person);

    assertThat(errors, hasError("Is adult."));

  }
}