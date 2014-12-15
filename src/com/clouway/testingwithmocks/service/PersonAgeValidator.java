package com.clouway.testingwithmocks.service;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:26 Oct 14-10-17
 */
public class PersonAgeValidator implements Validator{

  @Override
  public boolean validate(Person person) {
    if(person.getAge()>=10 && person.getAge()<=100){
        return true;
    }
    return false;
  }
}
