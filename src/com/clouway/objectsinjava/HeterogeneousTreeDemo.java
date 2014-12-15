package com.clouway.objectsinjava;

public class HeterogeneousTreeDemo {
  public static void main(String[] args) {
    Car car1 = new Car(50, "Opel");
    Car car2 = new Car(40, "BMW");
    Car car3 = new Car(60, "Lada");
    Car car4 = new Car(55, "zzzz");
    Car car5 = new Car(62, "ztzzz");
    HeterogeneousTree tree = new HeterogeneousTree();


    tree.addObject(car1);
    tree.addObject(car2);
    tree.addObject(car3);
    tree.addObject(car4);
    tree.addObject(car1);

    tree.inOrderTraverse();

    double abc = 23.4355;
    if (tree.search(car4) != null) {
      System.out.println(String.format("the element %s was found. %s", car4, car4));

    } else {
      System.out.println("No Object found found.");
    }

  }
}
