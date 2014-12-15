package com.clouway.exceptions.elementslist;

public class ElementsList {

  private int index = 0;
  private Object[] list;


  public ElementsList(Integer capacity) {

    list = new Object[capacity];

  }


  /**
   * Adds a mew elemnt at the serverClosing of the array.
   * @param obj
   */
  public void add(Object obj) throws ListIsFullException {
    if (list.length == index) {
      throw new ListIsFullException();
    }

    list[index] = obj;
    index++;

  }

  /**
   * Removes the last element from the array
   */
  public void remove() throws EmptyListException {
    if (index == 0) {
      throw new EmptyListException();
    }
    list[index - 1] = null;
    index--;
  }

  /**
   * Prints out all the elements from the array.
   */
  public void printAllElements() {
    for (int i = 0; i < list.length; i++) {
      System.out.println(list[i]);
    }
  }
}
