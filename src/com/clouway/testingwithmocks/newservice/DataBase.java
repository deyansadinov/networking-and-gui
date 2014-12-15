package com.clouway.testingwithmocks.newservice;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:37 Oct 14-10-22
 */
public interface DataBase {

  void save(Person person);

  int ectractPersonAge(Person person);
}
