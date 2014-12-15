package com.clouway.objectsinjava;

public class Car implements Comparable<Object> {

  private final Integer SPEED;
  private final String MODEL;

  public Car(Integer speed, String name) {
    this.SPEED = speed;
    this.MODEL = name;
  }

  @Override
  public String toString() {
    return this.SPEED + " || " + MODEL;
  }


  @Override
  public int compareTo(Object o) {
    Car otherCar = (Car) o;
    return this.SPEED.compareTo(otherCar.SPEED);
  }
}
