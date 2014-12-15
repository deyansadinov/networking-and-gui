package com.clouway.exceptions.elementslist;

import com.clouway.exceptions.console.Cigars;

public class ElementsListDemo {
  public static void main(String[] args) {

    ElementsList eList = new ElementsList(4);

    Cigars cig1 = new Cigars("Victory");
    Cigars cig2 = new Cigars("GD");
    Cigars cig3 = new Cigars("MM");
    Cigars cig4 = new Cigars("Shipka");
    Cigars cig5 = new Cigars("Devil");

    try {
      eList.add(cig1);
      eList.add(cig2);
      eList.add(cig3);
      eList.add(cig4);

    } catch (ListIsFullException e) {
      System.out.println(e.toString());
    }
  //  eList.printAllElements();

      eList.remove();
    eList.remove();
    eList.printAllElements();



  }
}
