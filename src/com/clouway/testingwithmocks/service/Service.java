package com.clouway.testingwithmocks.service;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:40 Oct 14-10-17
 */

public class Service {

  private Person person;

  public Service(Person person) {

    this.person = person;
  }

  public int getPersonAge() {
    return person.getAge();
  }

  public void save(MySQL mySQL, Validator validator) {
    if(validator.validate(person)) {
      mySQL.saveInDB(person);
    }
  }

  public boolean isMature(MySQL mySQL) {
    if(mySQL.getFromDB(person)>=18) return true;
   return false;
  }
}
