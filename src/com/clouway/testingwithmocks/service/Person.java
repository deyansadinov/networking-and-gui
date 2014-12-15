package com.clouway.testingwithmocks.service;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:15 Oct 14-10-17
 */
public class Person {
  private String age;


  public Person(String age) {

    this.age = age;
  }

  public Integer getAge() {
    return Integer.parseInt(age);
  }
}
