package com.clouway.testingwithmocks.service;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:58 Oct 14-10-17
 */
public interface MySQL {
  void saveInDB(Person person);
  int getFromDB(Person person);
}
