package com.clouway.testingwithmocks.newservice;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:09 Oct 14-10-22
 */
public class Person {

  private final String name;
  private final String age;

  public Person(String name, String age) {
    this.name = name;
    this.age = age;
  }

  public String getAge() {
    return age;
  }
}
