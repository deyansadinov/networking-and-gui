package com.clouway.testingwithmocks.today;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 17:00 Nov 14-11-19
 */
public class Service {

  public Errors isAdult(Person person){
    if(person.getAge()<18){
      return new Errors("Is not adult.");
    }
    return new Errors("Is adult.");


  }


}
