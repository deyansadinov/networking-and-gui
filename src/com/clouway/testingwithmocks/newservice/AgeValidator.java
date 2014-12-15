package com.clouway.testingwithmocks.newservice;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:24 Oct 14-10-24
 */
public class AgeValidator implements Validator {
  @Override
  public boolean validateAge(Person person) {
    String personAge = person.getAge();
    int age = 0;
    try {
      age = Integer.parseInt(personAge);
    }catch (NumberFormatException e){
     return false;
    }
    if(Integer.parseInt(personAge)<10|| Integer.parseInt(personAge)>100){
      return false;
    }
    return true;
  }
}
