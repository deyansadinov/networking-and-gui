package com.clouway.testing.sample.newshop;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 9:17 Oct 14-10-23
 */
public class ShopSellTest {

  @Test(expected = ShopOutOfProductsException.class)
  public void emptyShop() throws Exception {
    Shop shop = new Shop();

    shop.sell("apple",4);
  }

  @Test
  public void sellProduct() throws Exception {

    Shop shop = new Shop();
    Product apple = new Product("apple", 2.30);

    shop.addNewProduct(apple, 100, 20);

    assertThat(shop.sell("apple", 9), is(11));
  }

  @Test
  public void twoProducts() throws Exception {
    Shop shop = new Shop();

    Product apple = new Product("apple", 2.30);
    Product banana = new Product("banana", 4.30);

    shop.addNewProduct(apple, 100, 20);
    shop.addNewProduct(banana, 50, 30);

    assertThat(shop.sell("apple", 9), is(11));
    assertThat(shop.sell("banana", 10), is(20));

  }

  @Test(expected = ErrorInShopException.class)
  public void tooBigQuantity() throws Exception {
    Shop shop = new Shop();

    Product apple = new Product("apple", 2.30);

    shop.addNewProduct(apple, 100, 20);
    shop.sell("apple", 21);

  }


  @Test(expected = ProductNotFoundException.class)
  public void productThatDoesNotExist() throws Exception {
    Shop shop = new Shop();

    Product apple = new Product("apple", 2.30);

    shop.addNewProduct(apple, 100, 20);

    shop.sell("mango", 10);
  }


}
