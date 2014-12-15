package com.clouway.testing.sample.newshop;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:54 Oct 14-10-23
 */
public class OrderSellTest {

  @Test
  public void order() throws Exception {
    Shop shop = new Shop();
    Order order = new Order();
    Product apple = new Product("apple", 2.30);
    Product orange = new Product("orange", 3.30);
    Product lemon = new Product("lemon", 4.30);

    shop.addNewProduct(apple, 100, 20);
    shop.addNewProduct(orange, 100, 20);
    shop.addNewProduct(lemon, 100, 20);

    order.addOrder("apple", 3);

    Map orderToSell = order.addOrder("orange", 5);

    shop.sellOrder((java.util.HashMap<String, Integer>) orderToSell);

    assertThat(shop.sell("apple", 1), is(16));
  }
}
