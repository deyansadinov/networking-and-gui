package com.clouway.testing.sample.newshop;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ShopAddTest {

  @Test
  public void addToProductMoreQuantity() throws Exception {

    Shop shop = new Shop();

    Product apple = new Product("apple", 2.30);

    shop.addNewProduct(apple, 100, 20);

    assertThat(shop.add("apple", 10), is(30));
  }

  @Test(expected = ErrorInShopException.class)
  public void addTooBigQuantity() throws Exception {
    Shop shop = new Shop();

    Product apple = new Product("apple", 2.30);

    shop.addNewProduct(apple, 100, 20);

    shop.add("apple", 90);
  }

}