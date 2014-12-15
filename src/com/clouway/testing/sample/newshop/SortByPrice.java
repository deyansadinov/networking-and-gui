package com.clouway.testing.sample.newshop;

import java.util.Comparator;


/**
 * @author Ivan Genchev <ivan.genchev1989@gmail.com> 11:49 Oct 14-10-21
 */
public class SortByPrice implements Comparator<Product> {


  @Override
  public int compare(Product o1, Product o2) {
    return o1.getPrice().compareTo(o2.getPrice());
  }
}
