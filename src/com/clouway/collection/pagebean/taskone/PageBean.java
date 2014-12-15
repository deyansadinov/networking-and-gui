package com.clouway.collection.pagebean.taskone;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PageBean {

  private final int pagesize;
  private final List<Integer> elements;
  private int index = 0;
  private int pageNumber = 0;

  public PageBean(List<Integer> elements, int pageSize) {
    this.pagesize = pageSize;
    this.elements = elements;
  }

  /**
   * returns true or false if there is a next element;
   *
   * @return
   */
  public boolean hasNext() throws IndexOutOfBoundsException {
    return index < elements.size();
  }

  /**
   * returns true or false if there is a previous element;
   *
   * @return
   */
  public boolean hasPrevious() {
    for (ListIterator it = elements.listIterator(index - 1); it.hasPrevious(); ) {
      if (it.hasPrevious()) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the current page number.
   */
  public int getCurrentPageNumber() {
    if (pageNumber > elements.size() / pagesize) {
      pageNumber = 1;
    }
    return pageNumber;
  }

  public void firstPage() {
    pageNumber = 1;
    index = 1;
  }

  /**
   * Gets the last page and makes it to be the current one.
   */
  public void lastPage() {
    pageNumber = elements.size() / pagesize;
    index = elements.lastIndexOf(elements.size()) + 1;
  }

  /**
   * Prints the next elements.
   */
  public List<Object> next() {
    List<Object> output = new ArrayList<>();
    if ((index) >= elements.size()) {
      System.out.println("Back to the start of the page.");
      index = 0;
    }
    if ((index + pagesize) > elements.size()) {
      System.out.println(elements.get(index));
    } else {
      for (Object d : elements.subList(index, pagesize + index)) {
        output.add(d);
      }
    }
    index += pagesize;
    System.out.println();
    pageNumber++;
    return output;
  }

  /**
   * Rerturns the previous elemnts it they exist
   */
  public List<Object> previous() {
    List<Object> output = new ArrayList<>();
    index -= pagesize * 2;
    for (Object d : elements.subList(index, index + pagesize)) {
      output.add(d);
    }
    index += pagesize;
    System.out.println();
    if (index < 0) {
      throw new IndexOutOfBoundsException();
    }
    pageNumber--;
    return output;
  }
}
