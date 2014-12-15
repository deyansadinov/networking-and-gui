package com.clouway.testing.sample.newshop;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:21 Oct 14-10-23
 */
public class ShopSortTest {
  @Test
  public void byPrice() throws Exception {
    Shop shop = new Shop();


    Product a = new Product("a", 2.30);
    Product b = new Product("b", 4.30);
    Product c = new Product("c", 5.30);

    shop.addNewProduct(c, 100, 20);
    shop.addNewProduct(b, 50, 30);
    shop.addNewProduct(a, 50, 30);

    List<Product> list = shop.sort(new SortByPrice());

    assertThat(list.get(0), is(a));
    assertThat(list.get(2), is(c));
  }
}
