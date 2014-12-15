package com.clouway.testingwithmocks.newservice;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 14:42 Oct 14-10-22
 */
public class Service {

  public void saveToDB(Person person, Validator vaLidator, DataBase dataBase) {
    if (vaLidator.validateAge(person)) {
      dataBase.save(person);
    }
  }

  public boolean isAdult(Person person, DataBase dataBase) {
    if (dataBase.ectractPersonAge(person) < 18) {
      return false;
    }
    return true;
  }
}
