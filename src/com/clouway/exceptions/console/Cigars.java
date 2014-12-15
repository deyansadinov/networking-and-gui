package com.clouway.exceptions.console;

/**
 * Created by clouway on 14-9-17.
 */
public class Cigars {
  String name;

  public Cigars(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Cigars :" + name;
  }
}
