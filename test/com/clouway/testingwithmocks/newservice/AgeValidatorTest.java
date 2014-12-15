package com.clouway.testingwithmocks.newservice;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AgeValidatorTest {

  @Test
  public void ageNotValid() throws Exception {
    AgeValidator ageValidator=new AgeValidator();
    Person person=new Person("II","abc");

    assertThat(ageValidator.validateAge(person),is(false));
  }

  @Test
  public void validateAgeFalse() throws Exception {
    AgeValidator ageValidator=new AgeValidator();
    Person person=new Person("II","111");

    assertThat(ageValidator.validateAge(person),is(false));

  }

}