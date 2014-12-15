package com.clouway.testing.sample.newshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:40 Oct 14-10-23
 */
public class Order{

  private HashMap<String,Integer> orders = new HashMap<>();

  public HashMap<String,Integer> addOrder(String name, Integer quantity) {
   orders.put(name,quantity);
    return orders;
  }

}
